package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.property.dto.PropertyCorrectionDTO;
import shop.xianbao.modules.property.dto.PropertyCorrectionListDTO;
import shop.xianbao.modules.property.entity.PropertyCorrectionEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
public interface PropertyCorrectionService extends BaseService<PropertyCorrectionEntity> {
    PageData<PropertyCorrectionListDTO> page(Map<String, Object> params);

    List<PropertyCorrectionListDTO> list(Map<String, Object> params);

    List<PropertyCorrectionDTO> comboList(Map<String, Object> params);

    PropertyCorrectionDTO get(Long id);

    boolean add(PropertyCorrectionDTO dto);

    boolean update(PropertyCorrectionDTO dto);

    int delete(Long[] ids);

    /**
     * @param dto
     * @return
     */
    Boolean updateStatus(PropertyCorrectionDTO dto);
}