package shop.xianbao.modules.paytype.controller;

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
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.paytype.dto.PayTypeDTO;
import shop.xianbao.modules.paytype.dto.PayTypeListDTO;
import shop.xianbao.modules.paytype.service.PayTypeService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 支付方式
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
@RestController
@RequestMapping("/paytype/paytype")
@Api(tags = "支付方式")
public class PayTypeController {

    @Autowired
    private PayTypeService payTypeService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody PayTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        payTypeService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("paytype:paytype:info")
    public Result<PayTypeDTO> get(@PathVariable("id") Long id) {
        PayTypeDTO data = payTypeService.get(id);

        return new Result<PayTypeDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("paytype:paytype:update")
    public Result update(@RequestBody PayTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        payTypeService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("paytype:paytype:page")
    public Result<PageData<PayTypeListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<PayTypeListDTO> page = payTypeService.page(params);

        return new Result<PageData<PayTypeListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("paytype:paytype:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        payTypeService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("paytype:paytype:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<PayTypeListDTO> list = payTypeService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, PayTypeExcel.class);
    //    }
}