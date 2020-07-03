package shop.xianbao.modules.advertising.controller;

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
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.service.AdvService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@RestController
@RequestMapping("/advertising/adv")
@Api(tags = "广告表")
public class AdvController {

    @Autowired
    private AdvService advService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("advertising:adv:save")
    public Result save(@RequestBody AdvDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        advService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("advertising:adv:info")
    public Result<AdvDTO> get(@PathVariable("id") Long id) {
        AdvDTO data = advService.get(id);

        return new Result<AdvDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("advertising:adv:update")
    public Result update(@RequestBody AdvDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        advService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("advertising:adv:page")
    public Result<PageData<AdvDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AdvDTO> page = advService.advList(params);

        return new Result<PageData<AdvDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("advertising:adv:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        advService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("advertising:adv:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<AdvListDTO> list = advService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, AdvExcel.class);
    //    }
}