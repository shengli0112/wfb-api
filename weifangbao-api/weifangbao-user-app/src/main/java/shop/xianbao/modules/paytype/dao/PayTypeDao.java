package shop.xianbao.modules.paytype.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

/**
 * 支付方式
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
@Mapper
public interface PayTypeDao extends BaseDao<PayTypeEntity> {

}