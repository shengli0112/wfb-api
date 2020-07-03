/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 *
 * @author Tim tim@ruitukeji.com
 */
@RestController
@RequestMapping("/test")
@Api(tags="测试接口")
public class ApiTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation(value="获取用户信息", response=MemberDataEntity.class)
    public Result<MemberDataEntity> userInfo(@ApiIgnore @LoginUser MemberDataEntity user){
        return new Result<MemberDataEntity>().ok(user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public Result<Long> userInfo(@ApiIgnore @RequestAttribute("userId") Long userId){
        return new Result<Long>().ok(userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public Result<String> notToken(){
        return new Result<String>().ok("无需token也能访问。。。");
    }

}