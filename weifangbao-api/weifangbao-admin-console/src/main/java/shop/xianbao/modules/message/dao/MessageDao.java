package shop.xianbao.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.message.dto.MessageListDTO;
import shop.xianbao.modules.message.entity.MessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Mapper
public interface MessageDao extends BaseDao<MessageEntity> {
    List<MessageListDTO> getList(Map<String, Object> params);
}