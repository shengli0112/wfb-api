package shop.xianbao.modules.message.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.message.dto.MessageUserDTO;
import shop.xianbao.modules.message.dto.MessageUserListDTO;
import shop.xianbao.modules.message.entity.MessageUserEntity;
import shop.xianbao.modules.report.dto.ReportDTO;

import java.util.List;
import java.util.Map;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
public interface MessageUserService extends BaseService<MessageUserEntity> {
    PageData<MessageUserListDTO> page(Map<String, Object> params);

    List<MessageUserListDTO> list(Map<String, Object> params);

    List<MessageUserDTO> comboList(Map<String, Object> params);

    MessageUserDTO get(Long id);

    boolean add(MessageUserDTO dto);

    boolean update(MessageUserDTO dto);

    int delete(Long[] ids);

}