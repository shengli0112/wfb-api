package shop.xianbao.modules.helpcenter.type.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeDTO;
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeListDTO;
import shop.xianbao.modules.helpcenter.type.entity.HelpTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 帮助类型管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
public interface HelpTypeService extends BaseService<HelpTypeEntity> {

    void save(HelpTypeDTO dto);

    PageData<HelpTypeListDTO> page(Map<String, Object> params);

    void delete(Long[] ids);

    List<HelpTypeListDTO> selectAllType();
}