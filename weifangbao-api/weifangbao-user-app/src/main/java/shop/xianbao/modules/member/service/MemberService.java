package shop.xianbao.modules.member.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dto.*;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 会员用户表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
public interface MemberService extends BaseService<MemberEntity> {

    /**
     * 批量更新用户删除状态信息
     *
     * @param longs
     */
    void updateMemberInfo(List<Long> longs);

    /**
     * 根据手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    MemberEntity getValidByMobile(String mobile);

    /**
     * 更新单个用户信息
     *
     * @param dto
     */
    Result updateUserInfo(EditLoginInfoDTO dto);

    /**
     * 登录校验
     *
     * @param login 登录参数
     */
    Result login(LoginDTO login);

    /**
     * 退出登录
     *
     * @param userId
     */
    void logout(String userId);

    /**
     * 重置密码
     *
     * @param dto 用户信息
     */
    Result restPassword(RequestEditPasswordDTO dto);

    /**
     * 注册用户信息
     *
     * @param dto
     */
    void register(RegisterDTO dto);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    MemberEntity getUserByUserId(Long userId);

    /**
     * 获取用户数据
     *
     * @param mId 用户id
     * @return
     */
    MemberDataEntity getUserDataByMId(Long mId);

    /**
     * 获取用户数据
     *
     * @param mobile 用户id
     * @return
     */
    MemberDataEntity getMemberDataByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param login
     * @return
     */
    Result changePassword(EditLoginInfoDTO login);

    /**
     * 通过验证码修改密码
     *
     * @return
     */
    Result changePasswordBycaptcha(EditLoginInfoDTO dto);

    /**
     * 修改手机号
     *
     * @param dto
     * @param user
     * @return
     */
    Result changeMobile(CheckCaptchaDTO dto, MemberDataEntity user);

    /**
     * 邀请码校验
     *
     * @param dto
     * @return
     */
    public RegisterDTO inviteCodeCheck(RegisterDTO dto);

    /**
     * 已邀请好友列表
     */
    PageData<InviteUserListDTO> inviteUserList(Map<String, Object> params, Long unionId);

    /**
     * method: miniAppLogin
     *
     * @param json
     * @Description: 小程序登陆接口
     * @param: [json]
     * @return: shop.xianbao.common.utils.Result
     * @author: yh
     * @date: 2019/9/3 16:59
     */
    Result miniAppCodeLogin(MiniAppCodeLoginDTO json);

    Result miniAppMobileLogin(MiniAppMobileLoginDTO dto);

    /**
     * @param openId
     * @return
     */
    MemberDataEntity getByOpenId(String openId);

    Result getInviteQrCode(MemberDataEntity user, HttpServletResponse response);
}