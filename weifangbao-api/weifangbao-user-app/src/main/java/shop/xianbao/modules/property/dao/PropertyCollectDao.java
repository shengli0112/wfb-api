package shop.xianbao.modules.property.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.property.dto.PropertyCollectListDTO;
import shop.xianbao.modules.property.entity.PropertyCollectEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘收藏表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Mapper
public interface PropertyCollectDao extends BaseDao<PropertyCollectEntity> {
    List<PropertyCollectListDTO> getList(Map<String, Object> params);
}