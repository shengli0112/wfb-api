package shop.xianbao.modules.message.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.modules.message.dto.PushMessageListDTO;
import shop.xianbao.modules.message.entity.PushMessageEntity;
import shop.xianbao.modules.message.service.PushMessageService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@RestController
@RequestMapping("/message")
@Api(tags = "消息推送表")
public class PushMessageController {

    @Autowired
    private PushMessageService pushMessageService;

    //    @GetMapping("/{id}")
    //    @ApiOperation("信息")
    //    @RequiresPermissions("message:pushmessage:info")
    //    public Result<PushMessageDTO> get(@PathVariable("id") Long id){
    //        PushMessageDTO data = pushMessageService.get(id);
    //
    //        return new Result<PushMessageDTO>().ok(data);
    //    }

    //    @PutMapping
    //    @ApiOperation("修改")
    //    @LogOperation("修改")
    //    @RequiresPermissions("message:pushmessage:update")
    //    public Result update(@RequestBody PushMessageDTO dto){
    //        //效验数据
    //        ValidatorUtils.validateEntity(dto, UpdateGroup.class);
    //
    //        pushMessageService.update(dto);
    //
    //        return new Result();
    //    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    public Result<PageData<PushMessageListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<PushMessageListDTO> page = pushMessageService.page(params);

        return new Result<PageData<PushMessageListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        QueryWrapper<PushMessageEntity> wrapper = new QueryWrapper<>();
        wrapper.in("id", ids);
        wrapper.eq("user_type", PushMessageService.USER_TYPE_SELLER);
        //        wrapper.eq("user_id", SecurityUser.getUser().getSellerId());
        pushMessageService.softDelete(wrapper);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("message:pushmessage:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<PushMessageListDTO> list = pushMessageService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, PushMessageExcel.class);
    //    }
}