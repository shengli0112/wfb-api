package shop.xianbao.modules.message.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.message.dto.MessageRecordDTO;
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
public interface MessageRecordService extends BaseService<MessageRecordEntity> {
    PageData<MessageRecordListDTO> page(Map<String, Object> params);

    List<MessageRecordListDTO> list(Map<String, Object> params);

    List<MessageRecordDTO> comboList(Map<String, Object> params);

    MessageRecordDTO get(Long id);

    boolean add(MessageRecordDTO dto);

    boolean update(MessageRecordDTO dto);

    int delete(Long[] ids);
}