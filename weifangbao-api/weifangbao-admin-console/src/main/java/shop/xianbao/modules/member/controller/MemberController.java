/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.controller;

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
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.dto.MemberUserDTO;
import shop.xianbao.modules.member.dto.MemberUserListDTO;
import shop.xianbao.modules.member.service.MemberService;

import java.io.IOException;
import java.util.Map;

/**
 * 会员管理
 *
 * @author Tim tim@ruitukeji.com
 */
@RestController
@RequestMapping("/member")
@Api(tags = "会员管理")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/page")
    @ApiOperation("查询会员信息分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
    })
    @RequiresPermissions("member:member:page")
    public Result<PageData<MemberUserListDTO>> queryMemberList(@RequestParam Map<String, Object> params) {
        PageData<MemberUserListDTO> page = memberService.page(params);
        return new Result<PageData<MemberUserListDTO>>().ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "经纪人详情")
    @RequiresPermissions("member:member:info")
    public Result<MemberUserDTO> queryMemberInfo(@PathVariable("id") Long id) throws IOException {
        return memberService.get(id);
    }

    @PutMapping
    @ApiOperation("修改会员信息")
    @LogOperation("修改会员信息")
    @RequiresPermissions("member:member:update")
    public
    Result update(@RequestBody MemberUserDTO dto){
        //校验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        if(memberService.updateById(dto)){
            return new Result().ok();
        }
        return new Result().error();
    }

    @DeleteMapping
    @ApiOperation("批量删除会员用户")
    @LogOperation("批量删除会员用户")
    @RequiresPermissions("member:member:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        return new Result();
    }

    @GetMapping("/checkUser")
    @ApiOperation("检查用户是否存在")
    @RequiresPermissions("member:member:info")
    public Result get(@RequestParam String userName) {
        if (StringUtils.isEmpty(userName)) {
            return new Result().error(ErrorCode.PARAMS_GET_ERROR);
        }
        return memberService.queryMemberInfo(userName);
    }

}