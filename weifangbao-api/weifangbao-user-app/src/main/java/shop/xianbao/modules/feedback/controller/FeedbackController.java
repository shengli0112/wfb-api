package shop.xianbao.modules.feedback.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.annotation.NoLogin;
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
import shop.xianbao.modules.feedback.dto.FeedbackDTO;
import shop.xianbao.modules.feedback.dto.FeedbackListDTO;
import shop.xianbao.modules.feedback.excel.FeedbackExcel;
import shop.xianbao.modules.feedback.service.FeedbackService;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 意见反馈表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@RestController
@RequestMapping("feedback/feedback")
@Api(tags = "意见反馈表")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("feedback:feedback:page")
    public Result<PageData<FeedbackListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<FeedbackListDTO> page = feedbackService.page(params);

        return new Result<PageData<FeedbackListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("feedback:feedback:list")
    public Result<List<FeedbackListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<FeedbackListDTO> list = feedbackService.list(params);

        return new Result<List<FeedbackListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("feedback:feedback:comboList")
    public Result<List<FeedbackDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<FeedbackDTO> list = feedbackService.comboList(params);
        return new Result<List<FeedbackDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("feedback:feedback:info")
    public Result<FeedbackDTO> get(@PathVariable("id") Long id) {
        FeedbackDTO data = feedbackService.get(id);

        return new Result<FeedbackDTO>().ok(data);
    }

    @NoLogin
    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("feedback:feedback:add")
    public Result add(@RequestBody FeedbackDTO dto, @LoginUser MemberDataEntity user) {

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        return feedbackService.add(dto, user);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("feedback:feedback:update")
    public Result update(@RequestBody FeedbackDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        feedbackService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("feedback:feedback:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        feedbackService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("feedback:feedback:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<FeedbackListDTO> list = feedbackService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, FeedbackExcel.class);
    }

}