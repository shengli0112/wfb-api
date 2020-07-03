package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.member.entity.MemberLogLoginEntity;

/**
 * 会员登录日志
 *
 * @author wdp
 * @since 1.0.0 2019-01-09
 */
public interface MemberLogLoginService extends BaseService<MemberLogLoginEntity> {

    /**
     * 保存会员登录信息
     *
     * @param log
     */
    void save(MemberLogLoginEntity log);
}