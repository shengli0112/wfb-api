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
import shop.xianbao.modules.advertising.dto.AdvPositionDTO;
import shop.xianbao.modules.advertising.dto.AdvPositionListDTO;
import shop.xianbao.modules.advertising.service.AdvPositionService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 广告位表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@RestController
@RequestMapping("/advertising/advposition")
@Api(tags = "广告位表")
public class AdvPositionController {

    @Autowired
    private AdvPositionService advPositionService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("advertising:advposition:save")
    public Result save(@RequestBody AdvPositionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        advPositionService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("advertising:advposition:info")
    public Result<AdvPositionDTO> get(@PathVariable("id") Long id) {
        AdvPositionDTO data = advPositionService.get(id);

        return new Result<AdvPositionDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("advertising:advposition:update")
    public Result update(@RequestBody AdvPositionDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        advPositionService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("advertising:advposition:page")
    public Result<PageData<AdvPositionListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AdvPositionListDTO> page = advPositionService.page(params);

        return new Result<PageData<AdvPositionListDTO>>().ok(page);
    }

    @GetMapping("/list")
    @ApiOperation("不分页")
    @RequiresPermissions("advertising:advposition:page")
    public Result<List<AdvPositionListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AdvPositionListDTO> list = advPositionService.list(params);

        return new Result<List<AdvPositionListDTO>>().ok(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("advertising:advposition:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        advPositionService.delete(ids);

        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("advertising:advposition:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<AdvPositionListDTO> list = advPositionService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, AdvPositionExcel.class);
    //    }
}