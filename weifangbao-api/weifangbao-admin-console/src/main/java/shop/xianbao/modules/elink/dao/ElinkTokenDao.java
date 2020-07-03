package shop.xianbao.modules.elink.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.common.elink.entity.ElinkTokenEntity;

/**
 * 易联云平台访问令牌表
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Mapper
public interface ElinkTokenDao extends BaseDao<ElinkTokenEntity> {

    String selectElinkToken(@Param("clientId") String clientId);
}