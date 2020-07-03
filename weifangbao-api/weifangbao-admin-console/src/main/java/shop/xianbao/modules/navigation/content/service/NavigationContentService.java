package shop.xianbao.modules.navigation.content.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.navigation.content.dto.NavigationContentDTO;
import shop.xianbao.modules.navigation.content.dto.NavigationContentListDTO;
import shop.xianbao.modules.navigation.content.entity.NavigationContentEntity;

/**
 * 导航管理-导航内容
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
public interface NavigationContentService extends XianbaoCrudService<NavigationContentEntity, NavigationContentDTO, NavigationContentListDTO> {

}