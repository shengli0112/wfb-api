package shop.xianbao.modules.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dto.UserAuthCheckDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.member.service.UserAuthenticationService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@RestController
@RequestMapping("/member/userauthentication")
@Api(tags = "用户实名认证表")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @GetMapping("/{id}")
    @ApiOperation("信息")
    public Result<UserAuthenticationDTO> get(@PathVariable("id") Long id) {
        UserAuthenticationDTO data = userAuthenticationService.selectUserAuthById(id);
        return new Result<UserAuthenticationDTO>().ok(data);
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    public Result<PageData<UserAuthenticationListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<UserAuthenticationListDTO> page = userAuthenticationService.selectUserAuthPage(params);

        return new Result<PageData<UserAuthenticationListDTO>>().ok(page);
    }

    @PostMapping("/check")
    @ApiOperation("审核")
    @Transactional
    public Result check(@RequestBody UserAuthCheckDTO authCheckDTO) {
        return userAuthenticationService.check(authCheckDTO);
    }

}