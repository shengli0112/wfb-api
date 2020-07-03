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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberManageEntity;
import shop.xianbao.modules.member.service.MemberManageService;

import java.util.Map;

/**
 * 会员管理
 *
 * @author Tim tim@ruitukeji.com
 */
@RestController
@RequestMapping("/memberManage")
@Api(tags = "会员管理")
public class MemberManageController {

    @Autowired
    private MemberManageService memberManageService;

    @GetMapping("/memManagerPage")
    @ApiOperation("查询会员信息分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    public Result<PageData<MemberManageEntity>> memManagerPage(@RequestParam Map<String, Object> params) {
        PageData<MemberManageEntity> page = memberManageService.memManagerPage(params);
        return new Result<PageData<MemberManageEntity>>().ok(page);
    }

}