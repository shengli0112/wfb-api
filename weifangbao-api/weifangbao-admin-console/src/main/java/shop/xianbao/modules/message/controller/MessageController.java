package shop.xianbao.modules.message.controller;

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
import shop.xianbao.modules.message.dto.MessageDTO;
import shop.xianbao.modules.message.dto.MessageListDTO;
import shop.xianbao.modules.message.excel.MessageExcel;
import shop.xianbao.modules.message.service.MessageService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@RestController
@RequestMapping("message/message")
@Api(tags = "消息表")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    //    @RequiresPermissions("message:message:page")
    public Result<PageData<MessageListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<MessageListDTO> page = messageService.page(params);

        return new Result<PageData<MessageListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("message:message:list")
    public Result<List<MessageListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageListDTO> list = messageService.list(params);

        return new Result<List<MessageListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    //    @RequiresPermissions("message:message:comboList")
    public Result<List<MessageDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageDTO> list = messageService.comboList(params);
        return new Result<List<MessageDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    //    @RequiresPermissions("message:message:info")
    public Result<MessageDTO> get(@PathVariable("id") Long id) {
        MessageDTO data = messageService.get(id);

        return new Result<MessageDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    //    @RequiresPermissions("message:message:add")
    public Result add(@RequestBody MessageDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setType(1);
        messageService.sendMessage(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    //    @RequiresPermissions("message:message:update")
    public Result update(@RequestBody MessageDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        messageService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    //    @RequiresPermissions("message:message:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        messageService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("message:message:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MessageListDTO> list = messageService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, MessageExcel.class);
    }

}