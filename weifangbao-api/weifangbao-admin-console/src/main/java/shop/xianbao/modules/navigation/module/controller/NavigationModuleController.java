package shop.xianbao.modules.navigation.module.controller;

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
import shop.xianbao.modules.navigation.module.dto.NavigationModuleDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleListDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleTypeDTO;
import shop.xianbao.modules.navigation.module.service.NavigationModuleService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 导航管理-模块管理
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@RestController
@RequestMapping("/navigation/module")
@Api(tags = "导航管理-模块管理")
public class NavigationModuleController {

    @Autowired
    private NavigationModuleService navigationModuleService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("navigation:module:save")
    public Result save(@RequestBody NavigationModuleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        navigationModuleService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("navigation:module:info")
    public Result<NavigationModuleDTO> get(@PathVariable("id") Long id) {
        NavigationModuleDTO data = navigationModuleService.get(id);

        return new Result<NavigationModuleDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("navigation:module:update")
    public Result update(@RequestBody NavigationModuleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        navigationModuleService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("navigation:module:page")
    public Result<PageData<NavigationModuleListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<NavigationModuleListDTO> page = navigationModuleService.page(params);

        return new Result<PageData<NavigationModuleListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("navigation:module:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        navigationModuleService.delete(ids);

        return new Result();
    }

    @GetMapping("/type/{type}")
    @ApiOperation("根据查询类型(PC端和手机端)填充下拉框")
    @RequiresPermissions("navigation:module:type")
    public Result<List<NavigationModuleTypeDTO>> page(@PathVariable("type") String type) {
        List<NavigationModuleTypeDTO> page = navigationModuleService.selectByType(type);

        return new Result<List<NavigationModuleTypeDTO>>().ok(page);
    }

}