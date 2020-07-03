package shop.xianbao.modules.fastNavigation.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationDTO;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationListDTO;
import shop.xianbao.modules.fastNavigation.dto.IndexFastNavigationDTO;
import shop.xianbao.modules.fastNavigation.entity.FastNavigationEntity;

/**
 * 导航管理-导航内容
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
public interface FastNavigationService extends XianbaoCrudService<FastNavigationEntity, FastNavigationDTO, FastNavigationListDTO> {

    IndexFastNavigationDTO getIndexFastNavigation();
}