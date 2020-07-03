package shop.xianbao.modules.advertising.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.advertising.entity.AdvEntity;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Mapper
public interface AdvDao extends BaseDao<AdvEntity> {

}