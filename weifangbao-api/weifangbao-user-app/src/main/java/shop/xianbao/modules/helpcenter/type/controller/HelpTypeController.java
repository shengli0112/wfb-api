package shop.xianbao.modules.helpcenter.type.controller;

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
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeDTO;
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeListDTO;
import shop.xianbao.modules.helpcenter.type.service.HelpTypeService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

/**
 * 帮助类型管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@RestController
@RequestMapping("/helpcenter/type")
@Api(tags = "帮助类型管理")
public class HelpTypeController {

    @Autowired
    private HelpTypeService helpTypeService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("helpcenter:type:save")
    public Result save(@RequestBody HelpTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, Default.class);

        helpTypeService.save(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("helpcenter:type:page")
    public Result<PageData<HelpTypeListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<HelpTypeListDTO> page = helpTypeService.page(params);

        return new Result<PageData<HelpTypeListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("helpcenter:type:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        helpTypeService.delete(ids);

        return new Result();
    }

    @GetMapping("/all")
    @ApiOperation("所有类型")
    @RequiresPermissions("helpcenter:type:all")
    public Result<List<HelpTypeListDTO>> allType() {
        List<HelpTypeListDTO> list = helpTypeService.selectAllType();

        return new Result<List<HelpTypeListDTO>>().ok(list);
    }

}