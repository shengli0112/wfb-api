package shop.xianbao.modules.region.district.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.region.district.dto.SysDistrictListDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictSaveDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictUpdateDTO;
import shop.xianbao.modules.region.district.service.SysDistrictService;

import java.util.List;

/**
 * 五级地区(区或县)管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@RestController
@RequestMapping("/sys/district")
@Api(tags = "五级地区(区或县)管理")
public class SysDistrictController {

    @Autowired
    private SysDistrictService sysDistrictService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:district:save")
    public Result save(@RequestBody SysDistrictSaveDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        sysDistrictService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:district:info")
    public Result<SysDistrictUpdateDTO> get(@PathVariable("id") Long id) {
        SysDistrictUpdateDTO data = sysDistrictService.get(id);

        return new Result<SysDistrictUpdateDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:district:update")
    public Result update(@RequestBody SysDistrictUpdateDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        sysDistrictService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:district:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysDistrictService.delete(ids);

        return new Result();
    }

    @GetMapping("/list/{cityId}")
    @ApiOperation("列表")
    @RequiresPermissions("sys:district:list")
    public Result<List<SysDistrictListDTO>> list(@PathVariable("cityId") Long cityId) {

        List<SysDistrictListDTO> list = sysDistrictService.list(cityId);

        return new Result<List<SysDistrictListDTO>>().ok(list);
    }
}