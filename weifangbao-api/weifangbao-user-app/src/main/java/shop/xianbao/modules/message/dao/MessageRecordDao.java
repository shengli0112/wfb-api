package shop.xianbao.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.message.dto.MessageRecordListDTO;
import shop.xianbao.modules.message.entity.MessageRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@Mapper
public interface MessageRecordDao extends BaseDao<MessageRecordEntity> {
    List<MessageRecordListDTO> getList(Map<String, Object> params);
}