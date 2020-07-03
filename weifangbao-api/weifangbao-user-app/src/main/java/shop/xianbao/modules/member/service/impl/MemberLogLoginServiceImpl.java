package shop.xianbao.modules.member.service.impl;

import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.member.dao.MemberLogLoginDao;
import shop.xianbao.modules.member.entity.MemberLogLoginEntity;
import shop.xianbao.modules.member.service.MemberLogLoginService;

/**
 * 会员登录日志
 *
 * @author wdp
 * @since 1.0.0 2019-01-09
 */
@Service
public class MemberLogLoginServiceImpl extends BaseServiceImpl<MemberLogLoginDao, MemberLogLoginEntity> implements MemberLogLoginService {

    /**
     * 保存会员登录信息
     *
     * @param log 日志信息
     */
    @Override
    public void save(MemberLogLoginEntity log) {
        insert(log);
    }
}