package shop.xianbao.modules.customer.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.customer.dto.CustomerListDTO;
import shop.xianbao.modules.customer.entity.CustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@Mapper
public interface CustomerDao extends BaseDao<CustomerEntity> {
    List<CustomerListDTO> getList(Map<String, Object> params);
}