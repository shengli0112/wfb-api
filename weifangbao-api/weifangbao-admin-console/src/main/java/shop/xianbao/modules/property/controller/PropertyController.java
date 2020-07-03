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
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.excel.PropertyExcel;
import shop.xianbao.modules.property.service.PropertyService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@RestController
@RequestMapping("property/property")
@Api(tags = "楼盘表")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:property:page")
    public Result<PageData<PropertyListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<PropertyListDTO> page = propertyService.page(params);

        return new Result<PageData<PropertyListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:property:list")
    public Result<List<PropertyListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyListDTO> list = propertyService.list(params);

        return new Result<List<PropertyListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("property:property:comboList")
    public Result<List<PropertyDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyDTO> list = propertyService.comboList(params);
        return new Result<List<PropertyDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("property:property:info")
    public Result<PropertyDTO> get(@PathVariable("id") Long id) {
        PropertyDTO data = propertyService.get(id);

        return new Result<PropertyDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("property:property:add")
    public Result add(@RequestBody PropertyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        propertyService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("property:property:update")
    public Result update(@RequestBody PropertyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        propertyService.update(dto);

        return new Result();
    }

    @PutMapping("check")
    @ApiOperation("审核")
    @LogOperation("审核")
    @RequiresPermissions("property:property:update")
    public Result check(@RequestBody PropertyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        return propertyService.check(dto);
    }

    @PutMapping("isTop")
    @ApiOperation("置顶")
    @LogOperation("置顶")
    @RequiresPermissions("property:property:update")
    public Result isTop(@RequestBody PropertyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        return propertyService.isTop(dto);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("property:property:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        return propertyService.delete(ids);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("property:property:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PropertyListDTO> list = propertyService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PropertyExcel.class);
    }

}