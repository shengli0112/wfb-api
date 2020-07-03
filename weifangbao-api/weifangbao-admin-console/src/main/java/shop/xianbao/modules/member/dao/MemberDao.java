package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.dto.MemberUserDTO;
import shop.xianbao.modules.member.dto.MemberUserListDTO;
import shop.xianbao.modules.member.dto.UserMemberDTO;
import shop.xianbao.modules.member.entity.MemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {

    /**
     * 逻辑删除用户
     *
     * @param params 参数
     */
    void updateUserDeleted(@Param("params") Map<String, Object> params);

    /**
     * 批量查询用户注册信息
     *
     * @param ids
     * @return
     */
    List<MemberEntity> queryBathMemberInfo(List<Long> ids);

    List<UserMemberDTO> getAllMemberId();

    /**
     * @param params
     * @return
     */
    List<MemberUserListDTO> getList(Map<String, Object> params);

    MemberUserDTO get(Long id);
}