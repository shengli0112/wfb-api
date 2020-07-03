package shop.xianbao.modules.property.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.property.dto.PropertyRegionListDTO;
import shop.xianbao.modules.property.entity.PropertyRegionEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Mapper
public interface PropertyRegionDao extends BaseDao<PropertyRegionEntity> {
    List<PropertyRegionListDTO> getList(Map<String, Object> params);
}