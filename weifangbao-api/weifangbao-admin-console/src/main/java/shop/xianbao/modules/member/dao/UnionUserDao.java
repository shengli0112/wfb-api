package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

/**
 * 基础用户表（登录验证）
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Mapper
public
interface UnionUserDao extends BaseDao<UnionUserEntity>{

}