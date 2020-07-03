package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.dto.MemberUserListDTO;
import shop.xianbao.modules.member.entity.MemberBaseinfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberBaseinfoDao extends BaseDao<MemberBaseinfoEntity> {

    /**
     * 查询会员信息
     *
     * @param params
     * @return
     */
    List<MemberUserListDTO> getList(Map<String, Object> params);

    /**
     * 逻辑删除用户信息
     *
     * @param params 参数
     */
    void updateBathByIds(@Param("params") Map<String, Object> params);

    /**
     * 查询用户使用的等级信息
     *
     * @return
     */
    List<Long> queryMemberLevelUsedStatus();
}