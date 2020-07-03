package shop.xianbao.modules.appversion.controller;

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
import shop.xianbao.modules.appversion.dto.SysAppVersionDTO;
import shop.xianbao.modules.appversion.dto.SysAppVersionListDTO;
import shop.xianbao.modules.appversion.service.SysAppVersionService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author will
 * @since 1.0.0 2019-03-22
 */
@RestController
@RequestMapping("/appversion")
@Api(tags = "")
public class SysAppVersionController {

    @Autowired
    private SysAppVersionService sysAppVersionService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("appversion:appversion:save")
    public Result save(@RequestBody SysAppVersionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        sysAppVersionService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("appversion:appversion:info")
    public Result<SysAppVersionDTO> get(@PathVariable("id") Long id) {
        SysAppVersionDTO data = sysAppVersionService.get(id);

        return new Result<SysAppVersionDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("appversion:appversion:update")
    public Result update(@RequestBody SysAppVersionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        sysAppVersionService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("appversion:appversion:page")
    public Result<PageData<SysAppVersionListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysAppVersionListDTO> page = sysAppVersionService.page(params);

        return new Result<PageData<SysAppVersionListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("appversion:appversion:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysAppVersionService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("appversion:appversion:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<SysAppVersionListDTO> list = sysAppVersionService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, SysAppVersionExcel.class);
    //    }
}