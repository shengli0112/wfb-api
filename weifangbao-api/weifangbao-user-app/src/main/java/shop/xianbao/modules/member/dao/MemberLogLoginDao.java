package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberLogLoginEntity;

/**
 * 会员登录日志
 *
 * @author wdp
 * @since 1.0.0 2019-01-09
 */
@Mapper
public interface MemberLogLoginDao extends BaseDao<MemberLogLoginEntity> {

}