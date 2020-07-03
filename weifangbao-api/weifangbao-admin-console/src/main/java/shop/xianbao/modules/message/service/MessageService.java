package shop.xianbao.modules.message.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.message.dto.MessageDTO;
import shop.xianbao.modules.message.dto.MessageListDTO;
import shop.xianbao.modules.message.entity.MessageEntity;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;

import java.util.List;
import java.util.Map;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
public interface MessageService extends BaseService<MessageEntity> {
    PageData<MessageListDTO> page(Map<String, Object> params);

    List<MessageListDTO> list(Map<String, Object> params);

    List<MessageDTO> comboList(Map<String, Object> params);

    MessageDTO get(Long id);

    boolean sendMessage(MessageDTO dto);

    /**
     * @param dto
     * @param unionIds
     * @return
     */
    boolean add(MessageDTO dto, List<Long> unionIds);

    /**
     * @param type     消息类型 1平台消息  2业务消息
     * @param title
     * @param content
     * @param image
     * @param url
     * @param targetId
     * @param unionIds
     * @return
     */
    boolean add(Integer type, String title, String content, String image, String url, Long targetId, List<Long> unionIds);

    boolean update(MessageDTO dto);

    int delete(Long[] ids);

    void sendInviteMessage(ReportDTO reportDTO);

    void sendAuthMessage(Long unionId, String tel);

    void sendInvalidMessage(List<ReportListDTO> dtoList);

    void sendWillInvalidMessage(List<ReportListDTO> willInvalidList);

    void sendPropertyMessage(List<Long> unionIdList, PropertyDTO propertyDTO, String tel);
}