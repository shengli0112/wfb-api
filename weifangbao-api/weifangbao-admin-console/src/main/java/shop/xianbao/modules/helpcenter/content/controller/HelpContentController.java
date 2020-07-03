package shop.xianbao.modules.helpcenter.content.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentListDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentSaveDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentUpdateDTO;
import shop.xianbao.modules.helpcenter.content.service.HelpContentService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 帮助内容管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@RestController
@RequestMapping("/helpcenter/content")
@Api(tags = "帮助内容管理")
public class HelpContentController {

    @Autowired
    private HelpContentService helpContentService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("helpcenter:content:save")
    public Result save(HelpContentSaveDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        helpContentService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    //    @RequiresPermissions("helpcenter:content:info")
    public Result<HelpContentSaveDTO> get(@PathVariable("id") Long id) {
        HelpContentSaveDTO data = helpContentService.get(id);

        return new Result<HelpContentSaveDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("helpcenter:content:update")
    public Result update(HelpContentUpdateDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        helpContentService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("helpcenter:content:page")
    public Result<PageData<HelpContentListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<HelpContentListDTO> page = helpContentService.page(params);

        return new Result<PageData<HelpContentListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("helpcenter:content:delete")
    public Result delete(@RequestBody Long[] ids) {

        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        helpContentService.delete(ids);

        return new Result();
    }
}