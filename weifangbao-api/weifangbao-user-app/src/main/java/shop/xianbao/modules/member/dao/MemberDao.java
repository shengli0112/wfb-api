package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberEntity;

import java.util.List;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {

    /**
     * 逻辑删除用户信息
     *
     * @param ids
     */
    void updateBathByIds(List<Long> ids);

    /**
     * 获取用户数据
     *
     * @param mobile
     */
    MemberDataEntity getMemberDataByMobile(String mobile);

    /**
     * 获取用户数据
     *
     * @param mid
     */
    MemberDataEntity getMemberDataByMid(Long mid);

    MemberDataEntity getMemberDataByOpenId(String openId);

    MemberDataEntity getMemberDataByUnionId(Long unionId);
}