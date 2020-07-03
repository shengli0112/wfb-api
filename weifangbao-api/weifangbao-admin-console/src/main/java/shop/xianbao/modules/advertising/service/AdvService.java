package shop.xianbao.modules.advertising.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.dto.AdvListDTO;
import shop.xianbao.modules.advertising.entity.AdvEntity;

import java.util.Map;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
public interface AdvService extends XianbaoCrudService<AdvEntity, AdvDTO, AdvListDTO> {

    public PageData<AdvDTO> advList(Map<String, Object> params);
}