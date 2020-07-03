package shop.xianbao.modules.message.controller;

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
import shop.xianbao.modules.message.dto.MessageRecordDTO;
import shop.xianbao.modules.message.dto.MessageRecordListDTO;
import shop.xianbao.modules.message.excel.MessageRecordExcel;
import shop.xianbao.modules.message.service.MessageRecordService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@RestController
@RequestMapping("message/messagerecord")
@Api(tags = "消息记录表")
public class MessageRecordController {
    @Autowired
    private MessageRecordService messageRecordService;

    @Login
    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<PageData<MessageRecordListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        params.put("userId", user.getUnionId());
        PageData<MessageRecordListDTO> page = messageRecordService.page(params);
        return new Result<PageData<MessageRecordListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("message:messagerecord:list")
    public Result<List<MessageRecordListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageRecordListDTO> list = messageRecordService.list(params);

        return new Result<List<MessageRecordListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("message:messagerecord:comboList")
    public Result<List<MessageRecordDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageRecordDTO> list = messageRecordService.comboList(params);
        return new Result<List<MessageRecordDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("message:messagerecord:info")
    public Result<MessageRecordDTO> get(@PathVariable("id") Long id) {
        MessageRecordDTO data = messageRecordService.get(id);

        return new Result<MessageRecordDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("message:messagerecord:add")
    public Result add(@RequestBody MessageRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        messageRecordService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("message:messagerecord:update")
    public Result update(@RequestBody MessageRecordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        messageRecordService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("message:messagerecord:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        messageRecordService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("message:messagerecord:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MessageRecordListDTO> list = messageRecordService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, MessageRecordExcel.class);
    }

}