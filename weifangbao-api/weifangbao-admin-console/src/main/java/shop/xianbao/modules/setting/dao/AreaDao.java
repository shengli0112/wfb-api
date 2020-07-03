package shop.xianbao.modules.setting.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.setting.entity.AreaEntity;

/**
 * 地区表/行政划分表
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@Mapper
public interface AreaDao extends BaseDao<AreaEntity> {

}