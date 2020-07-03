package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dto.PropertyCollectDTO;
import shop.xianbao.modules.property.dto.PropertyCollectListDTO;
import shop.xianbao.modules.property.entity.PropertyCollectEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘收藏表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
public interface PropertyCollectService extends BaseService<PropertyCollectEntity> {
    PageData<PropertyCollectListDTO> page(Map<String, Object> params, MemberDataEntity user);

    List<PropertyCollectListDTO> list(Map<String, Object> params);

    List<PropertyCollectDTO> comboList(Map<String, Object> params);

    PropertyCollectDTO get(Long id);

    /**
     * @param dto
     * @param user
     * @return
     */
    boolean add(PropertyCollectDTO dto, MemberDataEntity user);

    boolean update(PropertyCollectDTO dto);

    int delete(Long[] ids);

    Result collect(PropertyCollectDTO dto, MemberDataEntity user);

    /**
     * @param propertyId
     * @param unionId
     * @return
     */
    PropertyCollectDTO getCollect(Long propertyId, Long unionId);
}