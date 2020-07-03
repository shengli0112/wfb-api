package shop.xianbao.modules.elink.controller;

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
import shop.xianbao.modules.elink.dto.ElinkTokenDTO;
import shop.xianbao.modules.elink.dto.ElinkTokenListDTO;
import shop.xianbao.modules.elink.service.ElinkTokenService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 易联云平台访问令牌表
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@RestController
@RequestMapping("/mapper/elink/elinktoken")
@Api(tags = "易联云平台访问令牌表")
public class ElinkTokenController {

    @Autowired
    private ElinkTokenService elinkTokenService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("elink:elinktoken:save")
    public Result save(@RequestBody ElinkTokenDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        elinkTokenService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("elink:elinktoken:info")
    public Result<ElinkTokenDTO> get(@PathVariable("id") Long id) {
        ElinkTokenDTO data = elinkTokenService.get(id);

        return new Result<ElinkTokenDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("elink:elinktoken:update")
    public Result update(@RequestBody ElinkTokenDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        elinkTokenService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("elink:elinktoken:page")
    public Result<PageData<ElinkTokenListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<ElinkTokenListDTO> page = elinkTokenService.page(params);

        return new Result<PageData<ElinkTokenListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("elink:elinktoken:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        elinkTokenService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("elink:elinktoken:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<ElinkTokenListDTO> list = elinkTokenService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, ElinkTokenExcel.class);
    //    }
}