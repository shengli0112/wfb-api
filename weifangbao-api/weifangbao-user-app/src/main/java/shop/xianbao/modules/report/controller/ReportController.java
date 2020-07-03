package shop.xianbao.modules.report.controller;

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
import shop.xianbao.modules.report.dto.AddReportDTO;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.report.excel.ReportExcel;
import shop.xianbao.modules.report.service.ReportService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@RestController
@RequestMapping("report/report")
@Api(tags = "交易表")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Login
    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<PageData<ReportListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        PageData<ReportListDTO> page = reportService.page(params, user);
        return new Result<PageData<ReportListDTO>>().ok(page);
    }

    @Login
    @GetMapping("my/page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<PageData<ReportListDTO>> myPage(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        PageData<ReportListDTO> page = reportService.myPage(params, user);
        return new Result<PageData<ReportListDTO>>().ok(page);
    }

    @Login
    @PutMapping("status")
    @ApiOperation("修改状态")
    @LogOperation("修改状态")
    public Result updateStatus(@RequestBody ReportDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        return reportService.updateStatus(dto);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("report:report:list")
    public Result<List<ReportListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<ReportListDTO> list = reportService.list(params);

        return new Result<List<ReportListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("report:report:comboList")
    public Result<List<ReportDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<ReportDTO> list = reportService.comboList(params);
        return new Result<List<ReportDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("report:report:info")
    public Result<ReportDTO> get(@PathVariable("id") Long id) {
        ReportDTO data = reportService.getReportInfo(id);

        return new Result<ReportDTO>().ok(data);
    }

    @Login
    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("report:report:add")
    public Result add(@RequestBody ReportDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        return reportService.add(dto, user);
    }

    @Login
    @PostMapping("batch")
    @ApiOperation("批量新增")
    @LogOperation("批量新增")
    @RequiresPermissions("report:report:add")
    public Result addBatch(@RequestBody List<AddReportDTO> dtos, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dtos, AddGroup.class, DefaultGroup.class);

        return reportService.addBatch(dtos, user);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("report:report:update")
    public Result update(@RequestBody ReportDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        reportService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("report:report:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        reportService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("report:report:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<ReportListDTO> list = reportService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, ReportExcel.class);
    }

}