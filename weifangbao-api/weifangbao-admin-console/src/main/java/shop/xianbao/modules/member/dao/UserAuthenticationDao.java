package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Mapper
public interface UserAuthenticationDao extends BaseDao<UserAuthenticationEntity> {

    List<UserAuthenticationListDTO> selectUserAuthPage(Map<String, Object> params);

    Integer selectCount(@Param("username") String username, @Param("nickname") String nickname);

    UserAuthenticationDTO selectUserAuthById(@Param("id") Long id);

    void updateUnionUserAuth(Long unionId, Integer isRealAuth);
}