package shop.xianbao.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.message.dao.PushMessageDao;
import shop.xianbao.modules.message.dto.PushMessageDTO;
import shop.xianbao.modules.message.dto.PushMessageListDTO;
import shop.xianbao.modules.message.entity.PushMessageEntity;
import shop.xianbao.modules.message.service.PushMessageService;

import java.util.Map;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Service
public class PushMessageServiceImpl extends XianbaoCrudServiceImpl<PushMessageDao, PushMessageEntity, PushMessageDTO, PushMessageListDTO> implements PushMessageService {

    @Override
    public QueryWrapper<PushMessageEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<PushMessageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        wrapper.eq("user_type", USER_TYPE_SELLER);
        //        wrapper.eq("user_id", SecurityUser.getUser().getSellerId());
        wrapper.lt("push_time", System.currentTimeMillis() / 1000);
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public PageData<PushMessageListDTO> page(Map<String, Object> params) {
        return super.page(params);
    }
}