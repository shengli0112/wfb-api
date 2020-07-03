package shop.xianbao.modules.menu.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.menu.entity.AppMenuEntity;

/**
 * app首页菜单表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
@Mapper
public interface AppMenuDao extends BaseDao<AppMenuEntity> {

}