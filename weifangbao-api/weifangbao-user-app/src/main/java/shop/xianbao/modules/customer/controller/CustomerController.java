package shop.xianbao.modules.customer.controller;

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
import shop.xianbao.modules.customer.dto.CustomerDTO;
import shop.xianbao.modules.customer.dto.CustomerListDTO;
import shop.xianbao.modules.customer.excel.CustomerExcel;
import shop.xianbao.modules.customer.service.CustomerService;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@RestController
@RequestMapping("customer/customer")
@Api(tags = "客户表")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Login
    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<PageData<CustomerListDTO>> page(
        @ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        params.put("unionId", user.getUnionId());
        PageData<CustomerListDTO> page = customerService.page(params);

        return new Result<PageData<CustomerListDTO>>().ok(page);
    }

    @Login
    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<List<CustomerListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        params.put("unionId", user.getUnionId());
        List<CustomerListDTO> list = customerService.list(params);

        return new Result<List<CustomerListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("customer:customer:comboList")
    public Result<List<CustomerDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<CustomerDTO> list = customerService.comboList(params);
        return new Result<List<CustomerDTO>>().ok(list);
    }

    @Login
    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("customer:customer:info")
    public Result<CustomerDTO> get(@PathVariable("id") Long id) {
        CustomerDTO data = customerService.get(id);

        return new Result<CustomerDTO>().ok(data);
    }

    @Login
    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("customer:customer:add")
    public Result add(
        @RequestBody CustomerDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        customerService.add(dto, user.getUnionId());

        return new Result();
    }

    @Login
    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("customer:customer:update")
    public Result update(@RequestBody CustomerDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        customerService.update(dto);

        return new Result();
    }

    @Login
    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("customer:customer:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        customerService.delete(ids);
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("customer:customer:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CustomerListDTO> list = customerService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CustomerExcel.class);
    }

}