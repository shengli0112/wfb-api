package shop.xianbao.modules.property.controller;

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
import shop.xianbao.common.utils.ExcelUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.property.dto.PropertyRegionDTO;
import shop.xianbao.modules.property.dto.PropertyRegionListDTO;
import shop.xianbao.modules.property.excel.PropertyRegionExcel;
import shop.xianbao.modules.property.service.PropertyRegionService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@RestController
@RequestMapping("property/propertyregion")
@Api(tags = "楼盘区域表")
public class PropertyRegionController {
    @Autowired
    private PropertyRegionService propertyRegionService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertyregion:page")
    public Result<PageData<PropertyRegionListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<PropertyRegionListDTO> page = propertyRegionService.page(params);

        return new Result<PageData<PropertyRegionListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertyregion:list")
    public Result<List<PropertyRegionListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyRegionListDTO> list = propertyRegionService.list(params);

        return new Result<List<PropertyRegionListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("property:propertyregion:comboList")
    public Result<List<PropertyRegionDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyRegionDTO> list = propertyRegionService.comboList(params);
        return new Result<List<PropertyRegionDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("property:propertyregion:info")
    public Result<PropertyRegionDTO> get(@PathVariable("id") Long id) {
        PropertyRegionDTO data = propertyRegionService.get(id);

        return new Result<PropertyRegionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("property:propertyregion:add")
    public Result add(@RequestBody PropertyRegionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        propertyRegionService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("property:propertyregion:update")
    public Result update(@RequestBody PropertyRegionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        propertyRegionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("property:propertyregion:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        return propertyRegionService.delete(ids);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("property:propertyregion:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PropertyRegionListDTO> list = propertyRegionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PropertyRegionExcel.class);
    }

}