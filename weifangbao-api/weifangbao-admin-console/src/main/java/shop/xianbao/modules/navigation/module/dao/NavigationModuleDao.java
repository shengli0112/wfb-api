package shop.xianbao.modules.navigation.module.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.navigation.module.entity.NavigationModuleEntity;

/**
 * 导航管理-模块管理
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Mapper
public interface NavigationModuleDao extends BaseDao<NavigationModuleEntity> {

}