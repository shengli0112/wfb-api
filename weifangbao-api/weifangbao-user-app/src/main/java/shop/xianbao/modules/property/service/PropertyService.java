package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
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
    PageData<PropertyListDTO> page(Map<String, Object> params, MemberDataEntity user);

    List<PropertyListDTO> list(Map<String, Object> params);

    List<PropertyDTO> comboList(Map<String, Object> params);

    PropertyDTO get(Long id, MemberDataEntity user);

    Result add(PropertyDTO dto, MemberDataEntity user);

    Result update(PropertyDTO dto, MemberDataEntity user);

    int delete(Long[] ids);

    Result updateStatus(PropertyDTO dto, MemberDataEntity user);

    /**
     * @param params
     * @param user
     * @return
     */
    PageData<PropertyListDTO> userPage(Map<String, Object> params, MemberDataEntity user);

    /**
     * 热推楼盘 最多5个
     * @param params
     * @param user
     * @return
     */
    List<PropertyListDTO> hotList(Map<String, Object> params, MemberDataEntity user);

}