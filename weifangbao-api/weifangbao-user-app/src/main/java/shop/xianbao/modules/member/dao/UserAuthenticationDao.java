package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Mapper
public interface UserAuthenticationDao extends BaseDao<UserAuthenticationEntity> {

}