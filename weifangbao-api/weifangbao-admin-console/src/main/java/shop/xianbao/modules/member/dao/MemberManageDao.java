package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberManageEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员管理
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberManageDao extends BaseDao<MemberManageEntity> {

    /**
     * 会员管理列表
     *
     * @return
     */
    List<MemberManageEntity> memManagePage(Map<String, Object> params);

    Integer memManagePageCount(Map<String, Object> params);
}