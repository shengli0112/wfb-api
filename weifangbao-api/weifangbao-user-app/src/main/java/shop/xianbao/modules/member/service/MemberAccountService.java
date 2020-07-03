package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.member.dto.MemberAccountDTO;
import shop.xianbao.modules.member.entity.MemberAccountEntity;

/**
 * 会员账目统计表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
public interface MemberAccountService extends BaseService<MemberAccountEntity> {

    /**
     * 查询账户明细
     *
     * @param id
     * @return
     */
    MemberAccountDTO get(Long id);

    /**
     * 新增账户信息
     *
     * @param dto
     */
    void save(MemberAccountDTO dto);
}