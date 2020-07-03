package shop.xianbao.modules.fastNavigation.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.fastNavigation.entity.FastNavigationEntity;

/**
 * 导航管理-导航内容
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
@Mapper
public interface FastNavigationDao extends BaseDao<FastNavigationEntity> {

}