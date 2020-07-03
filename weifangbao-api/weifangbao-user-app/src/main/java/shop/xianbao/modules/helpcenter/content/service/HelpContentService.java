package shop.xianbao.modules.helpcenter.content.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentListDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentSaveDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentUpdateDTO;
import shop.xianbao.modules.helpcenter.content.entity.HelpContentEntity;

import java.util.Map;

/**
 * 帮助内容表
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
public interface HelpContentService extends BaseService<HelpContentEntity> {

    void save(HelpContentSaveDTO dto);

    HelpContentUpdateDTO get(Long id);

    void update(HelpContentUpdateDTO dto);

    PageData<HelpContentListDTO> page(Map<String, Object> params);

    void delete(Long[] ids);
}