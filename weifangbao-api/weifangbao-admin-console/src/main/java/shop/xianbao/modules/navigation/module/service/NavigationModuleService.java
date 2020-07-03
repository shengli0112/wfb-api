package shop.xianbao.modules.navigation.module.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleListDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleTypeDTO;
import shop.xianbao.modules.navigation.module.entity.NavigationModuleEntity;

import java.util.List;

/**
 * 导航管理-模块管理
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
public interface NavigationModuleService extends XianbaoCrudService<NavigationModuleEntity, NavigationModuleDTO, NavigationModuleListDTO> {

    /**
     * 根据模块类型填充下拉框
     *
     * @param type 导航模块类型 0-PC端,1-手机端
     */
    List<NavigationModuleTypeDTO> selectByType(String type);
}