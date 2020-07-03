package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.entity.PropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
public interface PropertyService extends BaseService<PropertyEntity> {
    PageData<PropertyListDTO> page(Map<String, Object> params);

    List<PropertyListDTO> list(Map<String, Object> params);

    List<PropertyDTO> comboList(Map<String, Object> params);

    PropertyDTO get(Long id);

    boolean add(PropertyDTO dto);

    boolean update(PropertyDTO dto);

    Result delete(Long[] ids);

    /**
     * @param params
     * @return
     */
    PageData<PropertyListDTO> userPage(Map<String, Object> params);

    Result check(PropertyDTO dto);

    Result isTop(PropertyDTO dto);
}