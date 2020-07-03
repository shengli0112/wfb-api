package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.dto.InviteUserListDTO;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础用户表（登录验证）
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Mapper
public interface UnionUserDao extends BaseDao<UnionUserEntity> {

    /**
     * 更新用户剩余金币金额
     *
     * @param addCount 增加数量
     * @param unionId  用户id
     */
    void addGoldCount(@Param("addCount") Integer addCount, @Param("unionId") Long unionId);

    List<InviteUserListDTO> getList(Map<String, Object> params);
}