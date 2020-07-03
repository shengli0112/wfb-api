package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberAccountRecordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员流水账表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberAccountRecordsDao extends BaseDao<MemberAccountRecordsEntity> {

    /**
     * 查询会员用户账户流水信息
     *
     * @param params
     * @return
     */
    List<MemberAccountRecordsEntity> getList(Map<String, Object> params);
}