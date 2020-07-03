package shop.xianbao.modules.setting.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.setting.dto.AreaDTO;
import shop.xianbao.modules.setting.dto.AreaListDTO;
import shop.xianbao.modules.setting.service.AreaService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 地区表/行政划分表
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@RestController
@RequestMapping("/setting/area")
@Api(tags = "地区表/行政划分表")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("setting:area:save")
    public Result save(@RequestBody AreaDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        areaService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("setting:area:info")
    public Result<AreaDTO> get(@PathVariable("id") Long id) {
        AreaDTO data = areaService.get(id);
        return new Result<AreaDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("setting:area:update")
    public Result update(@RequestBody AreaDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);
        areaService.update(dto);

        return new Result();
    }

    @GetMapping("/list")
    //@RequiresPermissions("setting:area:list")
    public Result<List<AreaListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AreaListDTO> list = areaService.list(params);
        return new Result<List<AreaListDTO>>().ok(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("setting:area:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        areaService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("setting:area:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<AreaListDTO> list = areaService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, AreaExcel.class);
    //    }
}