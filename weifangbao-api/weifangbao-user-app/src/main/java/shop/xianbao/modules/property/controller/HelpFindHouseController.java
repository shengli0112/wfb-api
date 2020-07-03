package shop.xianbao.modules.property.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
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
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dto.HelpFindHouseDTO;
import shop.xianbao.modules.property.dto.HelpFindHouseListDTO;
import shop.xianbao.modules.property.excel.HelpFindHouseExcel;
import shop.xianbao.modules.property.service.HelpFindHouseService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@RestController
@RequestMapping("property/helpfindhouse")
@Api(tags = "帮找房表")
public class HelpFindHouseController {
    @Autowired
    private HelpFindHouseService helpFindHouseService;

    @GetMapping("page")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:helpfindhouse:page")
    public Result<PageData<HelpFindHouseListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<HelpFindHouseListDTO> page = helpFindHouseService.page(params);

        return new Result<PageData<HelpFindHouseListDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("查询列表不带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("property:helpfindhouse:list")
    public Result<List<HelpFindHouseListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<HelpFindHouseListDTO> list = helpFindHouseService.list(params);

        return new Result<List<HelpFindHouseListDTO>>().ok(list);
    }

    @GetMapping("comboList")
    @ApiOperation("下拉列表")
    @RequiresPermissions("property:helpfindhouse:comboList")
    public Result<List<HelpFindHouseDTO>> comboList(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<HelpFindHouseDTO> list = helpFindHouseService.comboList(params);
        return new Result<List<HelpFindHouseDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详细信息")
    @RequiresPermissions("property:helpfindhouse:info")
    public Result<HelpFindHouseDTO> get(@PathVariable("id") Long id) {
        HelpFindHouseDTO data = helpFindHouseService.get(id);
        return new Result<HelpFindHouseDTO>().ok(data);
    }

    @Login
    @GetMapping("info")
    @ApiOperation("详细信息")
    public Result<HelpFindHouseDTO> getInfo(@LoginUser MemberDataEntity user) {
        HelpFindHouseDTO data = helpFindHouseService.getInfo(user);
        return new Result<HelpFindHouseDTO>().ok(data);
    }

    @Login
    @PostMapping
    @ApiOperation("新增")
    @LogOperation("新增")
    @RequiresPermissions("property:helpfindhouse:add")
    public Result add(@RequestBody HelpFindHouseDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        return helpFindHouseService.add(dto, user);
    }

    @Login
    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("property:helpfindhouse:update")
    public Result update(@RequestBody HelpFindHouseDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        boolean update = helpFindHouseService.update(dto);
        if (update) {
            return new Result();
        }
        return new Result().error();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("property:helpfindhouse:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        helpFindHouseService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("property:helpfindhouse:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<HelpFindHouseListDTO> list = helpFindHouseService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, HelpFindHouseExcel.class);
    }

}