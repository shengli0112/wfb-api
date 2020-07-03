package shop.xianbao.modules.elink.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.elink.service.ElinkService;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.elink.dto.ElinkPrinterDTO;
import shop.xianbao.modules.elink.dto.ElinkPrinterListDTO;
import shop.xianbao.modules.elink.service.ElinkPrinterService;
import shop.xianbao.modules.elink.service.ElinkTokenService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 易联云打印机
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@RestController
@RequestMapping("/elink/elinkprinter")
@Api(tags = "易联云打印机")
public class ElinkPrinterController {

    @Autowired
    private ElinkService printerService;

    @Autowired
    private ElinkTokenService elinkTokenService;

    @Autowired
    private ElinkPrinterService elinkPrinterService;

    @GetMapping("/add_printer")
    public Result addPrinter(@RequestParam Long storeId, String machineCode, String mSign, String printName) {
        Integer num = elinkPrinterService.checkPrinter(machineCode);
        if (num > 0) {
            return new Result().error("该设备已被绑定");
        }
        String token = elinkTokenService.getToken();
        String result = printerService.addPrinter(token, machineCode, mSign, printName);
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            if ("0".equals(jsonObject.getString("error"))) {
                //插入数据库
                elinkPrinterService.savePrint(storeId, machineCode, mSign, printName);
                return new Result().ok("绑定成功");
            } else {
                return new Result().error(jsonObject.getString("error_description"));
            }
        }
        return new Result().error("绑定失败");
    }

    @GetMapping("/remove_printer")
    public Result removePrinter(@RequestParam Long storeId, String machineCode) {
        String token = elinkTokenService.getToken();
        String result = printerService.deletePrinter(token, machineCode);
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            if ("0".equals(jsonObject.getString("error"))) {
                elinkPrinterService.deletePrinter(storeId, machineCode);
                return new Result().ok("解绑成功");
            } else {
                return new Result().error(jsonObject.getString("error_description"));
            }
        }
        return new Result().error("解绑失败");
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("elink:elinkprinter:save")
    public Result save(@RequestBody ElinkPrinterDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        elinkPrinterService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    //    @RequiresPermissions("elink:elinkprinter:info")
    public Result<ElinkPrinterDTO> get(@PathVariable("id") Long id) {
        ElinkPrinterDTO data = elinkPrinterService.get(id);

        return new Result<ElinkPrinterDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    //    @RequiresPermissions("elink:elinkprinter:update")
    public Result update(@RequestBody ElinkPrinterDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        elinkPrinterService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    //    @RequiresPermissions("elink:elinkprinter:page")
    public Result<PageData<ElinkPrinterListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<ElinkPrinterListDTO> page = elinkPrinterService.page(params);
        return new Result<PageData<ElinkPrinterListDTO>>().ok(page);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    //    @RequiresPermissions("elink:elinkprinter:page")
    public Result<List<ElinkPrinterListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<ElinkPrinterListDTO> list = elinkPrinterService.list(params);
        return new Result<List<ElinkPrinterListDTO>>().ok(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    //    @RequiresPermissions("elink:elinkprinter:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        elinkPrinterService.delete(ids);

        return new Result();
    }

}