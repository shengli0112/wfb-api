package shop.xianbao.modules.property.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.entity.PropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Mapper
public interface PropertyDao extends BaseDao<PropertyEntity> {
    List<PropertyListDTO> getList(Map<String, Object> params);

    List<PropertyListDTO> getUserList(Map<String, Object> params);

    PropertyDTO get(Long id);
}