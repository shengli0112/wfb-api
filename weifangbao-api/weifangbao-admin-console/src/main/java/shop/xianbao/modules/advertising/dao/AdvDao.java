package shop.xianbao.modules.advertising.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.entity.AdvEntity;

import java.util.List;
import java.util.Map;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Mapper
public interface AdvDao extends BaseDao<AdvEntity> {

    /**
     * 广告列表
     *
     * @param params
     * @return
     */
    List<AdvDTO> advList(Map<String, Object> params);
}