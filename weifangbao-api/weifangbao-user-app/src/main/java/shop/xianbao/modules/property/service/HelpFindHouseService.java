package shop.xianbao.modules.property.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dto.HelpFindHouseDTO;
import shop.xianbao.modules.property.dto.HelpFindHouseListDTO;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.entity.HelpFindHouseEntity;

import java.util.List;
import java.util.Map;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
public interface HelpFindHouseService extends BaseService<HelpFindHouseEntity> {
    PageData<HelpFindHouseListDTO> page(Map<String, Object> params);

    List<HelpFindHouseListDTO> list(Map<String, Object> params);

    List<HelpFindHouseDTO> comboList(Map<String, Object> params);

    HelpFindHouseDTO get(Long id);

    Result add(HelpFindHouseDTO dto, MemberDataEntity user);

    boolean update(HelpFindHouseDTO dto);

    int delete(Long[] ids);

    HelpFindHouseDTO getInfo(MemberDataEntity user);

    /**
     * @param property
     */
    void helpFindHouse(PropertyDTO property);
}