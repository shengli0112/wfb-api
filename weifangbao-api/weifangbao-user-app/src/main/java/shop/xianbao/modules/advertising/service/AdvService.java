package shop.xianbao.modules.advertising.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.dto.AdvListDTO;
import shop.xianbao.modules.advertising.entity.AdvEntity;

import java.util.List;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
public interface AdvService extends XianbaoCrudService<AdvEntity, AdvDTO, AdvListDTO> {

    /**
     * 根据广告位置获取广告
     *
     * @param positionId
     * @return
     */
    List<AdvListDTO> getAdvByPosition(Long positionId);

}