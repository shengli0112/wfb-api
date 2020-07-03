package shop.xianbao.modules.member.controller;

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
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.dto.EditMemberLevelDTO;
import shop.xianbao.modules.member.dto.MemberLevelDTO;
import shop.xianbao.modules.member.service.MemberLevelService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 会员等级
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@RestController
@RequestMapping("/member/level")
@Api(tags = "会员等级")
public class MemberLevelController {

    @Autowired
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("member:memberlevel:page")
    public Result<PageData<MemberLevelDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<MemberLevelDTO> page = memberLevelService.page(params);

        return new Result<PageData<MemberLevelDTO>>().ok(page);
    }

    /**
     * 查询会员等级信息
     *
     * @param id 不传id则查询全部等级信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation("会员等级信息不分页")
    @RequiresPermissions("member:memberlevel:info")
    public Result<List<MemberLevelDTO>> get(Long id) {
        List<MemberLevelDTO> data = memberLevelService.queryMemberLevel(id);
        return new Result<List<MemberLevelDTO>>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("member:memberlevel:save")
    public Result save(@RequestBody MemberLevelDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        memberLevelService.save(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("member:memberlevel:update")
    public Result update(@RequestBody EditMemberLevelDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        memberLevelService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("member:memberlevel:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        memberLevelService.delete(ids);

        return new Result();
    }
}