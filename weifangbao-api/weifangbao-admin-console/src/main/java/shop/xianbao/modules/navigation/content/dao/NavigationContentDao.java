package shop.xianbao.modules.navigation.content.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.navigation.content.entity.NavigationContentEntity;

import java.util.List;
import java.util.Map;

/**
 * 导航管理-导航内容
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Mapper
public interface NavigationContentDao extends BaseDao<NavigationContentEntity> {

    List<NavigationContentEntity> getListType(Map<String, Object> params);
}