package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberLevelEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员等级
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberLevelDao extends BaseDao<MemberLevelEntity> {

    /**
     * 查询会员全部等级列表详情
     *
     * @param params
     * @return
     */
    List<MemberLevelEntity> getList(Map<String, Object> params);
}