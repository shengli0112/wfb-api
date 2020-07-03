package shop.xianbao.modules.navigation.content.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.navigation.content.dto.NavigationContentDTO;
import shop.xianbao.modules.navigation.content.dto.NavigationContentListDTO;
import shop.xianbao.modules.navigation.content.service.NavigationContentService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 导航管理-导航内容
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@RestController
@RequestMapping("/navigation/content")
@Api(tags = "导航管理-导航内容")
public class NavigationContentController {

    @Autowired
    private NavigationContentService navigationContentService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("navigation:content:save")
    public Result save(@RequestBody NavigationContentDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        // 检查链接地址
        boolean resultFlag = checkUrl(dto);
        if (!resultFlag) {
            return new Result().error(ErrorCode.URL_ERROR);
        }

        navigationContentService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("navigation:content:info")
    public Result<NavigationContentDTO> get(@PathVariable("id") Long id) {
        NavigationContentDTO data = navigationContentService.get(id);

        return new Result<NavigationContentDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("navigation:content:update")
    public Result update(@RequestBody NavigationContentDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        boolean resultFlag = checkUrl(dto);
        if (!resultFlag) {
            return new Result().error(ErrorCode.URL_ERROR);
        }

        navigationContentService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("navigation:content:page")
    public Result<PageData<NavigationContentListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<NavigationContentListDTO> page = navigationContentService.page(params);
        return new Result<PageData<NavigationContentListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("navigation:content:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        navigationContentService.delete(ids);

        return new Result();
    }

    /**
     * 根据 urlSourceType 的值校验链接地址来源
     */
    private boolean checkUrl(NavigationContentDTO dto) {
        boolean resultFlag = true;
        // 链接地址来源途径(方式)
        int urlSourceType = dto.getUrlSourceType();
        // 1-下拉框选取
        if (urlSourceType == 1) {
            if (dto.getModuleId() == null) {
                resultFlag = false;
            }

            // 2-手动输入地址
        } else if (urlSourceType == 2) {
            if (StringUtils.isBlank(dto.getCustomUrl())) {
                resultFlag = false;
            }
        }
        return resultFlag;
    }
}