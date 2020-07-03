package shop.xianbao.modules.fastNavigation.controller;

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
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationDTO;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationListDTO;
import shop.xianbao.modules.fastNavigation.service.FastNavigationService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 导航管理-导航内容
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
@RestController
@RequestMapping("/fastNavigation/fastnavigation")
@Api(tags = "导航管理-导航内容")
public class FastNavigationController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private FastNavigationService fastNavigationService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("fastNavigation:fastnavigation:save")
    public Result save(@RequestBody FastNavigationDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        fastNavigationService.save(dto);
        //处理redis缓存
        redisUtils.delete("index:navigation");
        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("fastNavigation:fastnavigation:info")
    public Result<FastNavigationDTO> get(@PathVariable("id") Long id) {
        FastNavigationDTO data = fastNavigationService.get(id);

        return new Result<FastNavigationDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("fastNavigation:fastnavigation:update")
    public Result update(@RequestBody FastNavigationDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        fastNavigationService.update(dto);
        //处理redis缓存
        redisUtils.delete("index:navigation");
        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("fastNavigation:fastnavigation:page")
    public Result<PageData<FastNavigationListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<FastNavigationListDTO> page = fastNavigationService.page(params);

        return new Result<PageData<FastNavigationListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("fastNavigation:fastnavigation:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        fastNavigationService.delete(ids);
        //处理redis缓存
        redisUtils.delete("index:navigation");
        return new Result();
    }

    /**
     * 导出excel场景需要调用的接口
     * 如果用不到请删除以下代码
     */
    //    @GetMapping("export")
    //    @ApiOperation("导出")
    //    @LogOperation("导出")
    //    @RequiresPermissions("fastNavigation:fastnavigation:export")
    //    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    //        List<FastNavigationListDTO> list = fastNavigationService.list(params);
    //        ExcelUtils.exportExcelToTarget(response, null, list, FastNavigationExcel.class);
    //    }
}