package shop.xianbao.modules.menu.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.menu.dto.AppMenuDTO;
import shop.xianbao.modules.menu.dto.AppMenuListDTO;
import shop.xianbao.modules.menu.entity.AppMenuEntity;

/**
 * app首页菜单表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
public interface AppMenuService extends XianbaoCrudService<AppMenuEntity, AppMenuDTO, AppMenuListDTO> {

}