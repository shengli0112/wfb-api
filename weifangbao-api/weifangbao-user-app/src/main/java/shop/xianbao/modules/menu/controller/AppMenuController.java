package shop.xianbao.modules.menu.controller;

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
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.menu.dto.AppMenuDTO;
import shop.xianbao.modules.menu.dto.AppMenuListDTO;
import shop.xianbao.modules.menu.service.AppMenuService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * app首页菜单表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
@RestController
@RequestMapping("/menu/appmenu")
@Api(tags = "app首页菜单表")
public class AppMenuController {

    @Autowired
    private AppMenuService appMenuService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("menu:appmenu:save")
    public Result save(@RequestBody AppMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        appMenuService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("menu:appmenu:info")
    public Result<AppMenuDTO> get(@PathVariable("id") Long id) {
        AppMenuDTO data = appMenuService.get(id);

        return new Result<AppMenuDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("menu:appmenu:update")
    public Result update(@RequestBody AppMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        appMenuService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("menu:appmenu:page")
    public Result<PageData<AppMenuListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AppMenuListDTO> page = appMenuService.page(params);

        return new Result<PageData<AppMenuListDTO>>().ok(page);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    @RequiresPermissions("menu:appmenu:list")
    public Result<List<AppMenuListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        params.put("menuPosition", 2);
        List<AppMenuListDTO> list = appMenuService.list(params);
        return new Result<List<AppMenuListDTO>>().ok(list);
    }

    @GetMapping("/list_by_position")
    @ApiOperation("列表")
    @RequiresPermissions("menu:appmenu:list")
    public Result<List<AppMenuListDTO>> listByPosition(@ApiIgnore @RequestParam Map<String, Object> params) {
        Object menuPositionObj = params.get("menuPosition");
        if (menuPositionObj == null || StringUtils.isBlank(menuPositionObj.toString())) {
            return new Result<List<AppMenuListDTO>>().error("menuPosition不能为空");
        }
        List<AppMenuListDTO> list = appMenuService.list(params);
        return new Result<List<AppMenuListDTO>>().ok(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("menu:appmenu:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appMenuService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("menu:appmenu:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<AppMenuListDTO> list = appMenuService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, AppMenuExcel.class);
    //    }
}