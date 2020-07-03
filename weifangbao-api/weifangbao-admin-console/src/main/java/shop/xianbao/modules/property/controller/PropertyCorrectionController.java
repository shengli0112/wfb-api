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
import shop.xianbao.modules.property.dto.PropertyCorrectionDTO;
import shop.xianbao.modules.property.dto.PropertyCorrectionListDTO;
import shop.xianbao.modules.property.excel.PropertyCorrectionExcel;
import shop.xianbao.modules.property.service.PropertyCorrectionService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@RestController
@RequestMapping("property/propertycorrection")
@Api(tags = "楼盘纠错表")
public class PropertyCorrectionController {
    @Autowired
    private PropertyCorrectionService propertyCorrectionService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertycorrection:page")
    public Result<PageData<PropertyCorrectionListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<PropertyCorrectionListDTO> page = propertyCorrectionService.page(params);

        return new Result<PageData<PropertyCorrectionListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertycorrection:list")
    public Result<List<PropertyCorrectionListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyCorrectionListDTO> list = propertyCorrectionService.list(params);

        return new Result<List<PropertyCorrectionListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("property:propertycorrection:comboList")
    public Result<List<PropertyCorrectionDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyCorrectionDTO> list = propertyCorrectionService.comboList(params);
        return new Result<List<PropertyCorrectionDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("property:propertycorrection:info")
    public Result<PropertyCorrectionDTO> get(@PathVariable("id") Long id) {
        PropertyCorrectionDTO data = propertyCorrectionService.get(id);

        return new Result<PropertyCorrectionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("property:propertycorrection:add")
    public Result add(@RequestBody PropertyCorrectionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        propertyCorrectionService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("property:propertycorrection:update")
    public Result updateStatus(@RequestBody PropertyCorrectionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        Boolean update = propertyCorrectionService.updateStatus(dto);
        if (update) {
            return new Result();
        } else {
            return new Result().error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("property:propertycorrection:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        propertyCorrectionService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("property:propertycorrection:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PropertyCorrectionListDTO> list = propertyCorrectionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PropertyCorrectionExcel.class);
    }

}