package shop.xianbao.modules.property.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.property.dto.HelpFindHouseListDTO;
import shop.xianbao.modules.property.entity.HelpFindHouseEntity;

import java.util.List;
import java.util.Map;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Mapper
public interface HelpFindHouseDao extends BaseDao<HelpFindHouseEntity> {
    List<HelpFindHouseListDTO> getList(Map<String, Object> params);
}