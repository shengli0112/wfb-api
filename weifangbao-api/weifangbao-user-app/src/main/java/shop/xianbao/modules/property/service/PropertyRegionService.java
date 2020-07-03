package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.property.dto.PropertyRegionDTO;
import shop.xianbao.modules.property.dto.PropertyRegionListDTO;
import shop.xianbao.modules.property.entity.PropertyRegionEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
public interface PropertyRegionService extends BaseService<PropertyRegionEntity> {
    PageData<PropertyRegionListDTO> page(Map<String, Object> params);

    List<PropertyRegionListDTO> list(Map<String, Object> params);

    List<PropertyRegionDTO> comboList(Map<String, Object> params);

    PropertyRegionDTO get(Long id);

    boolean add(PropertyRegionDTO dto);

    boolean update(PropertyRegionDTO dto);

    int delete(Long[] ids);

    /**
     * @param areaId
     * @return
     */
    String getAreaNameById(Long areaId);
}