package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberAccountDao;
import shop.xianbao.modules.member.dto.MemberAccountDTO;
import shop.xianbao.modules.member.entity.MemberAccountEntity;
import shop.xianbao.modules.member.service.MemberAccountService;

/**
 * 会员账目统计表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Service
public class MemberAccountServiceImpl extends BaseServiceImpl<MemberAccountDao, MemberAccountEntity> implements MemberAccountService {

    /**
     * 查询会员账户信息
     *
     * @param id
     * @return
     */
    @Override
    public MemberAccountDTO get(Long id) {
        QueryWrapper<MemberAccountEntity> memberAccountEntityQueryWrapper = new QueryWrapper<>();
        memberAccountEntityQueryWrapper.eq("uid", id);
        memberAccountEntityQueryWrapper.eq("is_deleted", 0);
        MemberAccountEntity memberAccountEntity = baseDao.selectOne(memberAccountEntityQueryWrapper);
        return ConvertUtils.sourceToTarget(memberAccountEntity, MemberAccountDTO.class);
    }

    /**
     * 新增用户账户信息
     *
     * @param dto 用户账户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MemberAccountDTO dto) {
        MemberAccountEntity memberAccountEntity = ConvertUtils.sourceToTarget(dto, MemberAccountEntity.class);
        baseDao.insert(memberAccountEntity);
    }
}