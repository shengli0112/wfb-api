package shop.xianbao.modules.property.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.property.dto.PropertyCorrectionListDTO;
import shop.xianbao.modules.property.entity.PropertyCorrectionEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Mapper
public interface PropertyCorrectionDao extends BaseDao<PropertyCorrectionEntity> {
    List<PropertyCorrectionListDTO> getList(Map<String, Object> params);
}