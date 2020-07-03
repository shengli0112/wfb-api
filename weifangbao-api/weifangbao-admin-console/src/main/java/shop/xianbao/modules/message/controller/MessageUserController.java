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
import shop.xianbao.modules.message.dto.MessageUserDTO;
import shop.xianbao.modules.message.dto.MessageUserListDTO;
import shop.xianbao.modules.message.excel.MessageUserExcel;
import shop.xianbao.modules.message.service.MessageUserService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@RestController
@RequestMapping("message/messageuser")
@Api(tags = "用户消息表")
public class MessageUserController {
    @Autowired
    private MessageUserService messageUserService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("message:messageuser:page")
    public Result<PageData<MessageUserListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<MessageUserListDTO> page = messageUserService.page(params);

        return new Result<PageData<MessageUserListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("message:messageuser:list")
    public Result<List<MessageUserListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageUserListDTO> list = messageUserService.list(params);

        return new Result<List<MessageUserListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("message:messageuser:comboList")
    public Result<List<MessageUserDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<MessageUserDTO> list = messageUserService.comboList(params);
        return new Result<List<MessageUserDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("message:messageuser:info")
    public Result<MessageUserDTO> get(@PathVariable("id") Long id) {
        MessageUserDTO data = messageUserService.get(id);

        return new Result<MessageUserDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("message:messageuser:add")
    public Result add(@RequestBody MessageUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        messageUserService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("message:messageuser:update")
    public Result update(@RequestBody MessageUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        messageUserService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("message:messageuser:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        messageUserService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("message:messageuser:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MessageUserListDTO> list = messageUserService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, MessageUserExcel.class);
    }

}