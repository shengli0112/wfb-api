package shop.xianbao.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.message.dto.MessageUserListDTO;
import shop.xianbao.modules.message.entity.MessageUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Mapper
public interface MessageUserDao extends BaseDao<MessageUserEntity> {
    List<MessageUserListDTO> getList(Map<String, Object> params);
}