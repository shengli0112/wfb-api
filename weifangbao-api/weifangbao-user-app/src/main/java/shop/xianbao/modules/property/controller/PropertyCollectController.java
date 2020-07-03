package shop.xianbao.modules.property.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
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
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dto.PropertyCollectDTO;
import shop.xianbao.modules.property.dto.PropertyCollectListDTO;
import shop.xianbao.modules.property.excel.PropertyCollectExcel;
import shop.xianbao.modules.property.service.PropertyCollectService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 楼盘收藏表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@RestController
@RequestMapping("property/propertycollect")
@Api(tags = "楼盘收藏表")
public class PropertyCollectController {
    @Autowired
    private PropertyCollectService propertyCollectService;

    @Login
    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertycollect:page")
    public Result<PageData<PropertyCollectListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        PageData<PropertyCollectListDTO> page = propertyCollectService.page(params, user);

        return new Result<PageData<PropertyCollectListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:propertycollect:list")
    public Result<List<PropertyCollectListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyCollectListDTO> list = propertyCollectService.list(params);

        return new Result<List<PropertyCollectListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("property:propertycollect:comboList")
    public Result<List<PropertyCollectDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<PropertyCollectDTO> list = propertyCollectService.comboList(params);
        return new Result<List<PropertyCollectDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("property:propertycollect:info")
    public Result<PropertyCollectDTO> get(@PathVariable("id") Long id) {
        PropertyCollectDTO data = propertyCollectService.get(id);

        return new Result<PropertyCollectDTO>().ok(data);
    }

    @Login
    @PostMapping
    @ApiOperation("收藏/取消收藏")
    @LogOperation("收藏/取消收藏")
    public Result collect(@RequestBody PropertyCollectDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        return propertyCollectService.collect(dto, user);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("property:propertycollect:update")
    public Result update(@RequestBody PropertyCollectDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        propertyCollectService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("property:propertycollect:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        propertyCollectService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("property:propertycollect:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PropertyCollectListDTO> list = propertyCollectService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PropertyCollectExcel.class);
    }

}