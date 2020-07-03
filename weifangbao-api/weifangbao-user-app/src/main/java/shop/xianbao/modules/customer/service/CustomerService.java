package shop.xianbao.modules.customer.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.customer.dto.CustomerDTO;
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
public interface CustomerService extends BaseService<CustomerEntity> {
    PageData<CustomerListDTO> page(Map<String, Object> params);

    List<CustomerListDTO> list(Map<String, Object> params);

    List<CustomerDTO> comboList(Map<String, Object> params);

    CustomerDTO get(Long id);

    boolean add(CustomerDTO dto, Long unionId);

    boolean update(CustomerDTO dto);

    int delete(Long[] ids);
}