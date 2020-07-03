package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberAccountEntity;

/**
 * 会员账目统计表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberAccountDao extends BaseDao<MemberAccountEntity> {

}