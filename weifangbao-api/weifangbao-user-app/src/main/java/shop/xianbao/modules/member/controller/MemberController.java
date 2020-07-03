package shop.xianbao.modules.member.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.CaptchaService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.dto.*;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.service.ApiCaptchaService;
import shop.xianbao.service.TokenService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ApiCaptchaService apiCaptchaService;

    @Autowired
    private UnionUserService unionUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 验证码注册
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);
        Result ret = new Result();
        //用户非重复性校验
        MemberDataEntity mbData = memberService.getMemberDataByMobile(dto.getMobile());
        if (mbData != null) {
            return ret.error(ErrorCode.REQUEST_ILLEGAL, "The mobile already registered");
        }
        //邀请码校验保存
        if (StringUtil.isNotEmpty(dto.getInviteCode())) {
            RegisterDTO rs = memberService.inviteCodeCheck(dto);
            if (!rs.getIsRight()) {
                return ret.error(ErrorCode.REQUEST_ILLEGAL, "邀请码不正确");
            } else {
                dto.setPId(rs.getPId());
                dto.setGId(rs.getGId());
            }
        }

        // 短信验证码校验
        if (!apiCaptchaService.checkCaptcha(dto.getMobile(), dto.getCaptcha(), CaptchaService.OptEnum.REGISTER, true)) {
            return ret.error(ErrorCode.REQUEST_ACCESS_CAPTCHA_WRONG);
        }

        memberService.register(dto);
        // return ret.ok();
        // 一下是自动登录流程
        LoginDTO login = new LoginDTO();
        login.setUsername(dto.getMobile());
        login.setPassword(dto.getPassword());
        login.setClient(dto.getClient());
        return login(login);
    }

    /**
     * 验证码校验
     *
     * @param dto
     * @return
     */
    @PostMapping("/checkCaptcha")
    public Result checkCaptcha(@RequestBody RegisterDTO dto) {
        //表单校验
        Result ret = new Result();
        //用户非重复性校验
        MemberDataEntity mbData = memberService.getMemberDataByMobile(dto.getMobile());
        if (mbData != null) {
            return ret.error(ErrorCode.REQUEST_ILLEGAL, "The mobile already registered");
        }
        // 短信验证码校验
        if (!apiCaptchaService.checkCaptcha(dto.getMobile(), dto.getCaptcha(), CaptchaService.OptEnum.REGISTER, false)) {
            return ret.error(ErrorCode.REQUEST_ACCESS_CAPTCHA_WRONG);
        }
        return ret.ok();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO login) {
        //校验数据
        ValidatorUtils.validateEntity(login);

        //TODO 建立用户登录日志
        //MemberLogLoginEntity log = new MemberLogLoginEntity();
        //log.setOperation(LoginOperationEnum.LOGIN.value());
        //log.setCreateDate(new Date());
        //log.setIp(IpUtils.getIpAddr(request));
        //log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        //log.setIp(IpUtils.getIpAddr(request));

        return memberService.login(login);
    }

    @Login
    @GetMapping("logout")
    public Result logout(@RequestHeader String token, HttpServletRequest request) {
        memberService.logout(token);

        // TODO 保存用户信息退出信息
        // MemberLogLoginEntity log = new MemberLogLoginEntity();
        // log.setOperation(LoginOperationEnum.LOGOUT.value());
        // log.setIp(IpUtils.getIpAddr(request));
        // log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        // log.setIp(IpUtils.getIpAddr(request));
        // log.setStatus(LoginStatusEnum.SUCCESS.value());
        // log.setCreator(user.getId());
        // log.setCreatorName(user.getUserName());
        // log.setCreateDate(new Date());
        // memberLogLoginService.save(log);
        return new Result().ok();
    }

    @PutMapping
    @LogOperation("修改登录信息")
    public Result updateLoginInfo(@RequestBody EditLoginInfoDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        return memberService.updateUserInfo(dto);

    }

    @PutMapping("/restPassword")
    @LogOperation("重置密码")
    public Result restPassword(@RequestBody RequestEditPasswordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        memberService.restPassword(dto);
        return new Result();
    }

    /**
     * 取得用户信息
     */
    @Login
    @GetMapping("/userInfo")
    public Result<UnionUserDTO> getInfo(@LoginUser MemberDataEntity user) {
        Long unionId = user.getUnionId();
        UnionUserDTO dto = unionUserService.get(unionId);
        return new Result<UnionUserDTO>().ok(dto);
    }

    //保存
    @Login
    @PostMapping("/update")
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result save(@RequestBody UnionUserDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        //ValidatorUtils.validateEntity(dto, UpdateGroup.class);
        dto.setId(user.getUnionId());
        unionUserService.update(dto);
        return new Result();
    }

    @PostMapping("/changePassword")
    @LogOperation("已知原密码修改密码")
    public Result changePassword(@RequestBody EditLoginInfoDTO dto) {
        return memberService.changePassword(dto);
    }

    /**
     * 通过验证码修改密码
     *
     * @return
     */
    @PostMapping("/changePasswordBycaptcha")
    public Result changePasswordBycaptcha(@RequestBody EditLoginInfoDTO dto) {
        return memberService.changePasswordBycaptcha(dto);
    }

    /**
     * 修改手机号
     *
     * @return
     */
    @Login
    @PostMapping("/changeMobile")
    public Result changeMobile(@RequestBody CheckCaptchaDTO dto, @LoginUser MemberDataEntity user) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);
        return memberService.changeMobile(dto, user);
    }

    @Login
    @PostMapping("/updateTerminalId")
    @ApiOperation("更新token表 设备终端id")
    @LogOperation("更新token表 设备终端id")
    public Result updateTerminalId(@RequestBody MemberTokenEntity to, @LoginUser MemberDataEntity user) {
        to.setToken(user.getToken().getToken());
        tokenService.updateTerminalId(to);
        return new Result();
    }

    //已邀请好友列表
    @Login
    @GetMapping("/invite/page")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result<PageData<InviteUserListDTO>> inviteUserList(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        PageData<InviteUserListDTO> pageData = memberService.inviteUserList(params, user.getUnionId());
        return new Result<PageData<InviteUserListDTO>>().ok(pageData);
    }

    /**
     * 邀请二维码
     * @return
     */
    @Login
    @GetMapping("/invite/qrcode")
    public Result getInviteQrCode(@LoginUser MemberDataEntity user, HttpServletResponse response) {
        return memberService.getInviteQrCode(user,response);
    }

    @PostMapping("/miniapp/login/code")
    public Result miniAppLoginByCode(@RequestBody MiniAppCodeLoginDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class);
        return memberService.miniAppCodeLogin(dto);
    }

    @PostMapping("/miniapp/login/mobile")
    public Result miniAppLoginByMobile(@RequestBody MiniAppMobileLoginDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class);
        return memberService.miniAppMobileLogin(dto);
    }
}