package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberAddressEntity;

/**
 * 用户收货地址表
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
@Mapper
public interface MemberAddressDao extends BaseDao<MemberAddressEntity> {

}