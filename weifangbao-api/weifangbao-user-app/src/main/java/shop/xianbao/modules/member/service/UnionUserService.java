package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.member.dto.UnionUserDTO;
import shop.xianbao.modules.member.dto.UnionUserListDTO;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 基础用户表（登录验证）
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
public interface UnionUserService extends XianbaoCrudService<UnionUserEntity, UnionUserDTO, UnionUserListDTO> {
    /**
     * 默认头像
     */
    String DEFAULT_AVATAR = "http://qiniu.ruitukeji.top/xianbao/20190304/351a24b5791846c3a9aa198a0ed8f102.jpg";

    /**
     * @param mobile 手机号
     */
    UnionUserEntity getEntityByMobile(String mobile);

    /**
     * 获取用户unionId
     *
     * @param unionId
     * @return
     */
    String getOpenId(Long unionId);

    /**
     * 更新用户账户金币
     *
     * @param unionId        用户id
     * @param newGoldAccount 新金币余额
     * @return
     */
    boolean updateUserGoldAccount(Long unionId, BigDecimal newGoldAccount);

    MemberDataEntity createMember(String phoneNumber, String openId, Long inviteId);

    List<UnionUserEntity> selectByIds(String unionIds);
}