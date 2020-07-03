package shop.xianbao.modules.member.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.constants.Constants;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.CaptchaService;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dao.MemberDao;
import shop.xianbao.modules.member.dao.UnionUserDao;
import shop.xianbao.modules.member.dto.*;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberEntity;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.pay.config.wx.WxMaUtils;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;
import shop.xianbao.modules.security.password.PasswordUtils;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;
import shop.xianbao.service.ApiCaptchaService;
import shop.xianbao.service.HttpService;
import shop.xianbao.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 会员用户表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberDao, MemberEntity> implements MemberService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiCaptchaService apiCaptchaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UnionUserService unionUserService;

    @Autowired
    private MemberService memberService;

    @Resource
    private UnionUserDao unionUserDao;

    @Resource
    private MemberDao memberDao;

    @Autowired
    private PayTypeService payTypeService;

    @Autowired
    private HttpService httpService;

    private WxMaService wxMaService;

    /**
     * 用户注册
     *
     * @param dto 用户注册信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterDTO dto) {

        //注册 UnionUser
        UnionUserEntity userEntity = new UnionUserEntity(dto.getMobile());
        // 密码加密
        String salt = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
        String password = PasswordUtils.encodeSalt(dto.getPassword(), salt);
        userEntity.setPassword(password);
        userEntity.setSalt(salt);
        userEntity.setNickname(dto.getMobile());
        userEntity.setPId(dto.getPId());
        userEntity.setGId(dto.getGId());
        userEntity.setOpenId(dto.getOpenId());
        unionUserService.insert(userEntity);

        //注册 Member
        MemberEntity memberEntity = new MemberEntity(userEntity.getId());
        memberService.insert(memberEntity);
    }

    /**
     * @param mId member.id
     */
    @Override
    public MemberDataEntity getUserDataByMId(Long mId) {
        if (mId == null) {
            return null;
        }
        return baseDao.getMemberDataByMid(mId);
    }

    /**
     * @param mobile 手机
     */
    @Override
    public MemberDataEntity getMemberDataByMobile(String mobile) {
        return baseDao.getMemberDataByMobile(mobile);
    }

    /**
     * 批量删除会员用户
     *
     * @param ids
     */
    @Override
    public void updateMemberInfo(List<Long> ids) {
        baseDao.updateBathByIds(ids);
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    @Override
    public MemberEntity getValidByMobile(String mobile) {
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        wrapper.eq("is_deleted", 0);
        wrapper.eq("is_locked", 0);
        MemberEntity memberEntity = baseDao.selectOne(wrapper);
        return memberEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateUserInfo(EditLoginInfoDTO dto) {

        QueryWrapper<MemberEntity> ew = new QueryWrapper<>();
        ew.eq("mobile", dto.getMobile());
        ew.eq("is_deleted", 0);
        MemberEntity wdpSysUserEntity = baseDao.selectOne(ew);
        if (wdpSysUserEntity == null) {
            return new Result().error(ErrorCode.DB_RECORD_NOT_EXISTS);
        }
        //密码错误
        //        if(!PasswordUtils.matchesSalt(dto.getOldPassword(), wdpSysUserEntity.getSalt(), wdpSysUserEntity.getPassword())){
        //            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        //        }
        //密码加密
        String random = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
        String password = PasswordUtils.encodeSalt(dto.getNewPassword(), random);
        //        wdpSysUserEntity.setPassword(password);
        //        wdpSysUserEntity.setSalt(random);
        //更新用户
        updateById(wdpSysUserEntity);
        return new Result();
    }

    /**
     * 登录校验查询
     *
     * @param login 登录参数
     * @throws XianbaoException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result login(LoginDTO login) {
        MemberDataEntity user = getMemberDataByMobile(login.getUsername());
        Result<LoginRetDTO> ret = new Result<LoginRetDTO>();
        //用户不存在
        if (user == null) {
            return ret.error(ErrorCode.REQUEST_ACCESS_UNREG);
        }

        //账号冻结
        if (user.getMisLocked() == 1 || user.getIsLocked() == 1) {
            return ret.error(ErrorCode.REQUEST_ACCESS_INVALID, "账号已经冻结");
        }
        //密码错误
        if (!PasswordUtils.matchesPassword(login.getPassword(), user.getSalt(), user.getPassword())) {
            return ret.error(ErrorCode.REQUEST_ACCESS_PWD_WRONG);
        }
        if (null == login.getTerminalId()) {
            login.setTerminalId("");
        }
        MemberTokenEntity token = tokenService.createToken(user.getMid(), login.getClient(), login.getTerminalId());
        // 登录异常
        if (token == null) {
            return ret.error(ErrorCode.SERVER_ERROR, "登录异常 获取token失败。");
        }
        return ret.ok(getLoginRetDTO(user, token));

    }

    private LoginRetDTO getLoginRetDTO(MemberDataEntity user, MemberTokenEntity token) {
        LoginRetDTO retDTO = new LoginRetDTO(user);
        TokenRetDTO tokenDTO = retDTO.getToken();
        tokenDTO.setToken(token.getToken());
        tokenDTO.setExpiresIn(TokenService.EXPIRE_IN);
        tokenDTO.setRefreshToken(""); //TODO
        return retDTO;
    }

    /**
     * 退出登录
     *
     * @param token
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(String token) {
        tokenService.expireToken(token);
    }

    @Override
    public Result restPassword(RequestEditPasswordDTO dto) {
        //短信验证码校验
        boolean flag = apiCaptchaService.checkCaptcha(dto.getMobile(), dto.getCaptcha(), CaptchaService.OptEnum.RESET_PWD, true);
        if (!flag) {
            return new Result().error(ErrorCode.CAPTCHA_ERROR);
        }
        MemberEntity entity = ConvertUtils.sourceToTarget(dto, MemberEntity.class);

        QueryWrapper<MemberEntity> ew = new QueryWrapper<>();
        ew.eq("mobile", dto.getMobile());
        ew.eq("is_deleted", 0);
        MemberEntity wdpSysUserEntity = baseDao.selectOne(ew);
        if (wdpSysUserEntity == null) {
            return new Result().error(ErrorCode.DB_RECORD_EXISTS);
        }
        //密码加密
        String random = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
        String password = PasswordUtils.encodeSalt(dto.getPassword(), random);
        //        entity.setPassword(password);
        entity.setUpdateDate(new Date());
        //更新用户
        updateById(entity);
        return new Result();
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return
     */
    @Override
    public MemberEntity getUserByUserId(Long id) {
        QueryWrapper<MemberEntity> memberEntityQueryWrapper = new QueryWrapper<>();
        memberEntityQueryWrapper.eq("id", id);
        return baseDao.selectOne(memberEntityQueryWrapper);
    }

    /**
     * 修改密码
     *
     * @param login
     * @return
     */
    @Override
    public Result changePassword(EditLoginInfoDTO login) {
        Result<LoginRetDTO> ret = new Result<LoginRetDTO>();
        MemberDataEntity user = memberService.getMemberDataByMobile(login.getMobile());
        //1.原密码错误
        if (!PasswordUtils.matchesPassword(login.getOldPassword(), user.getSalt(), user.getPassword())) {
            return ret.error(ErrorCode.REQUEST_ACCESS_PWD_WRONG, "原密码错误");
        } else {
            //2.原密码正确，更新密码
            String random = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
            String password = PasswordUtils.encodeSalt(login.getNewPassword(), random);
            user.setPassword(password);
            UnionUserEntity en = new UnionUserEntity();
            en.setId(user.getUnionId());
            en.setPassword(password);
            en.setSalt(random);
            unionUserDao.updateById(en);
            return new Result();
        }
    }

    /**
     * 通过验证码修改密码
     *
     * @return
     */
    @Override
    public Result changePasswordBycaptcha(EditLoginInfoDTO dto) {
        Result ret = new Result();

        //校验用户是否存在
        MemberDataEntity mbData = memberService.getMemberDataByMobile(dto.getMobile());
        if (mbData == null) {
            return ret.error(ErrorCode.REQUEST_ILLEGAL, "用户不存在");
        }

        // 短信验证码校验
        if (!apiCaptchaService.checkCaptcha(dto.getMobile(), dto.getCaptcha(), CaptchaService.OptEnum.RESET_PWD, true)) {
            return ret.error(ErrorCode.REQUEST_ACCESS_CAPTCHA_WRONG);
        }

        //验证码正确，更新密码
        String random = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
        String password = PasswordUtils.encodeSalt(dto.getNewPassword(), random);

        mbData.setPassword(password);
        UnionUserEntity en = new UnionUserEntity();
        en.setId(mbData.getUnionId());
        en.setPassword(password);
        en.setSalt(random);
        unionUserDao.updateById(en);
        return new Result();
    }

    @Override
    public Result changeMobile(CheckCaptchaDTO dto, MemberDataEntity user) {
        Result ret = new Result();

        //        //校验用户是否存在
        //        MemberDataEntity mbData = memberService.getMemberDataByMobile(dto.getOldMobile());
        //        if (mbData == null) {
        //            return ret.error(ErrorCode.REQUEST_ILLEGAL, "用户不存在");
        //        }
        //校验新手机号是否存在
        MemberDataEntity newData = memberService.getMemberDataByMobile(dto.getNewMobile());
        if (newData != null) {
            return ret.error(ErrorCode.REQUEST_ILLEGAL, "新手机号已存在");
        }

        // 短信验证码校验
        if (!apiCaptchaService.checkCaptcha(dto.getNewMobile(), dto.getCaptcha(), CaptchaService.OptEnum.CHANGE_MOBILE, true)) {
            return ret.error(ErrorCode.REQUEST_ACCESS_CAPTCHA_WRONG);
        }
        // 验证码校验通过 修改手机号 、登陆账号
        UnionUserEntity en = new UnionUserEntity();
        en.setId(user.getUnionId());
        en.setUsername(dto.getNewMobile());
        en.setMobile(dto.getNewMobile());
        en.setNickname(dto.getNewMobile());
        unionUserDao.updateById(en);
        return new Result();
    }

    /**
     * 邀请码校验
     *
     * @param dto
     * @return
     */
    @Override
    public RegisterDTO inviteCodeCheck(RegisterDTO dto) {
        QueryWrapper<UnionUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", dto.getInviteCode().trim());
        wrapper.eq("is_deleted", 0);
        UnionUserEntity en = unionUserDao.selectOne(wrapper);
        //邀请码不正确，返回false
        if (en == null) {
            dto.setIsRight(false);
            return dto;
        }
        dto.setIsRight(true);
        dto.setPId(en.getId());
        dto.setGId(en.getPId());
        return dto;
    }

    /**
     * 已邀请好友列表
     */
    @Override
    public PageData<InviteUserListDTO> inviteUserList(Map<String, Object> params, Long unionId) {
        params.put("pId", unionId);
        IPage page = getPage(params, "create_date", false);
        List<InviteUserListDTO> dtoList = unionUserDao.getList(params);
        return getPageData(dtoList, page.getTotal(), InviteUserListDTO.class);
    }

    //    private Result wxLogin(MemberDataEntity memberDataEntity) {
    //        LoginDTO loginDTO = new LoginDTO();
    //        loginDTO.setUsername(memberDataEntity.getUsername());
    //        loginDTO.setClient(5);
    //        return wxLogin(loginDTO);
    //    }

    @Transactional(rollbackFor = Exception.class)
    public Result wxLogin(LoginDTO login) {
        MemberDataEntity user = getMemberDataByMobile(login.getUsername());
        Result<LoginRetDTO> ret = new Result<LoginRetDTO>();
        //用户不存在
        if (user == null) {
            return ret.error(ErrorCode.REQUEST_ACCESS_UNREG);
        }

        //账号冻结
        if (user.getMisLocked() == 1 || user.getIsLocked() == 1) {
            return ret.error(ErrorCode.REQUEST_ACCESS_INVALID, "账号已经冻结");
        }
        if (null == login.getTerminalId()) {
            login.setTerminalId("");
        }
        MemberTokenEntity token = tokenService.createToken(user.getMid(), login.getClient(), login.getTerminalId());
        // 登录异常
        if (token == null) {
            return ret.error(ErrorCode.SERVER_ERROR, "登录异常 获取token失败。");
        }
        return ret.ok(getLoginRetDTO(user, token));

    }

    @Override
    public Result miniAppCodeLogin(MiniAppCodeLoginDTO dto) {
        Result result = new Result();
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("wechat_pay", "JSAPI_XCX");
        if (Objects.equals(null, payTypeEntity)) {
            return result.error("未配置微信小程序");
        }
        wxMaService = WxMaUtils.getWxMaService(payTypeEntity.getConfig());
        WxMaJscode2SessionResult codeResult;
        try {
            codeResult = wxMaService.jsCode2SessionInfo(dto.getCode());
        } catch (WxErrorException e) {
            throw new XianbaoException(e.getMessage());
        }
        String openId = codeResult.getOpenid();
        MemberDataEntity memberDataEntity = memberService.getByOpenId(openId);

        if (!Objects.equals(null, memberDataEntity)) {
            //存在则返回登录成功信息
            return wxLogin(memberDataEntity);
        }
        //不存在
        return result.error(ErrorCode.REQUEST_ACCESS_UNREG, openId);
    }

    private Result wxLogin(MemberDataEntity memberDataEntity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(memberDataEntity.getUsername());
        loginDTO.setClient(5);
        return wxLogin(loginDTO);
    }

    @Override
    public Result miniAppMobileLogin(MiniAppMobileLoginDTO dto) {
        Result result = new Result();
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("wechat_pay", "JSAPI_XCX");
        if (Objects.equals(null, payTypeEntity)) {
          return result.error("未配置微信小程序");
        }
        wxMaService = WxMaUtils.getWxMaService(payTypeEntity.getConfig());
        WxMaJscode2SessionResult codeResult;
        try {
            codeResult = wxMaService.jsCode2SessionInfo(dto.getCode());
        } catch (WxErrorException e) {
            throw new XianbaoException(e.getMessage());
        }
        String openId = codeResult.getOpenid();
        String encryptedData = dto.getEncryptedData();
        String sessionKey = codeResult.getSessionKey();
        String iv = dto.getIv();
        String mobileEncryptedData = dto.getMobileEncryptedData();
        String mobileIv = dto.getMobileIv();
        WxMaUserInfo userInfo = null;
        WxMaUserService userService = wxMaService.getUserService();
        log.debug("userService:{}", userService);
        if (StringUtils.isNotEmpty(encryptedData) && StringUtils.isNotEmpty(iv)) {
            userInfo = userService.getUserInfo(sessionKey, encryptedData, iv);
        }

        String phoneNumber = null;
        try {
            WxMaPhoneNumberInfo phoneNoInfo = userService.getPhoneNoInfo(sessionKey, mobileEncryptedData, mobileIv);
            //获取手机号
            phoneNumber = phoneNoInfo.getPhoneNumber();
        } catch (XianbaoException e) {
            throw new XianbaoException("获取手机号失败");
        }
        if (Objects.equals(null, phoneNumber)) {
            throw new XianbaoException("手机号获取失败,请重试");
        }
        //判断openId与phoneNumber是否存在
        MemberDataEntity memberDataEntity = this.getMemberDataByMobile(phoneNumber);
        if (!Objects.equals(null, memberDataEntity)) {
            //更新 用户信息 (昵称，头像，openId)
            updateUnionUser(userInfo, phoneNumber, memberDataEntity, openId);
            //存在则返回登录成功信息
        } else {
            //不存在则创建用户
            memberDataEntity = unionUserService.createMember(phoneNumber, openId, dto.getInviteId());
        }
        return wxLogin(memberDataEntity);
    }

    private void updateUnionUser(WxMaUserInfo userInfo, String phoneNumber, MemberDataEntity memberDataEntity, String openId) {
        UnionUserEntity unionUserEntity = new UnionUserEntity();
        unionUserEntity.setId(memberDataEntity.getUnionId());
        if (!Objects.equals(null, userInfo)) {
            unionUserEntity.setNickname(userInfo.getNickName());
            unionUserEntity.setAvatar(userInfo.getAvatarUrl());
        }
        unionUserEntity.setMobile(phoneNumber);
        unionUserEntity.setOpenId(openId);
        unionUserService.updateById(unionUserEntity);
    }

    @Override
    public MemberDataEntity getByOpenId(String openId) {
        return memberDao.getMemberDataByOpenId(openId);
    }

    @Override
    public
    Result getInviteQrCode(MemberDataEntity user, HttpServletResponse response){
        Result  result  = new Result();
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("wechat_pay", "JSAPI_XCX");
        if (Objects.equals(null, payTypeEntity)) {
            return result.error("未配置微信小程序");
        }
        wxMaService = WxMaUtils.getWxMaService(payTypeEntity.getConfig());
        try {
             File qrFile = wxMaService.getQrcodeService().createWxaCodeUnlimit(""+user.getUnionId(),"pages/account/invitate");
            httpService.downloadFile(qrFile,response);
        } catch (WxErrorException|IOException e) {
            throw new XianbaoException(e.getMessage());
        }
        return result.ok();
    }
}