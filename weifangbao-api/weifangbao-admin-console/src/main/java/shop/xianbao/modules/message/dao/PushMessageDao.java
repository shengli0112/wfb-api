package shop.xianbao.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.message.entity.PushMessageEntity;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Mapper
public interface PushMessageDao extends BaseDao<PushMessageEntity> {

}