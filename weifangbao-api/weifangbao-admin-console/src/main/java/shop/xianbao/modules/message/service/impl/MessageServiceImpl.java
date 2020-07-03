package shop.xianbao.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dto.UserMemberDTO;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.modules.message.dao.MessageDao;
import shop.xianbao.modules.message.dto.MessageDTO;
import shop.xianbao.modules.message.dto.MessageListDTO;
import shop.xianbao.modules.message.entity.MessageEntity;
import shop.xianbao.modules.message.entity.MessageUserEntity;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.message.service.MessageUserService;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MessageUserService messageUserService;

    @Override
    public PageData<MessageListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
        List<MessageListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), MessageListDTO.class);
    }

    @Override
    public List<MessageListDTO> list(Map<String, Object> params) {
        List<MessageListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<MessageDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<MessageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<MessageEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, MessageDTO.class);
    }

    @Override
    public MessageDTO get(Long id) {
        MessageEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, MessageDTO.class);
    }

    @Override
    public boolean sendMessage(MessageDTO dto) {
        List<UserMemberDTO> unionIds = memberService.getAllMemberId();
        boolean result = this.add(dto, unionIds.stream().map(UserMemberDTO::getId).collect(Collectors.toList()));
        List<String> openIds = unionIds.stream().filter(e -> StringUtils.isNotEmpty(e.getOpenId())).map(UserMemberDTO::getOpenId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(openIds)) {
            // TODO 微信消息
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(MessageDTO dto, List<Long> unionIds) {
        //保存用户消息
        if (CollectionUtils.isNotEmpty(unionIds)) {
            MessageEntity entity = ConvertUtils.sourceToTarget(dto, MessageEntity.class);
            insert(entity);
            List<MessageUserEntity> messageUserEntityList = new ArrayList<>();
            unionIds.forEach(unionId -> {
                MessageUserEntity messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(entity.getId());
                messageUserEntity.setUnionId(unionId);
                messageUserEntityList.add(messageUserEntity);
            });
            return messageUserService.insertBatch(messageUserEntityList);
        } else {
            return false;
        }

    }

    @Override
    public boolean add(Integer type, String title, String content, String image, String url, Long targetId, List<Long> unionIds) {
        MessageDTO dto = new MessageDTO(type, title, content, image, url, targetId);
        return add(dto, unionIds);
    }

    @Override
    public boolean update(MessageDTO dto) {
        MessageEntity entity = ConvertUtils.sourceToTarget(dto, MessageEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        UpdateWrapper<MessageEntity> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", 1);
        wrapper.in("id", Arrays.asList(ids));
        return baseDao.update(new MessageEntity(), wrapper);
    }

    @Async
    @Override
    public void sendInviteMessage(ReportDTO reportDTO) {
        MessageEntity messageEntity;
        String title = "客户到访";
        StringBuilder content = new StringBuilder();
        content.append("微房宝提醒您，您报备的").append(reportDTO.getCustomerName()).append(" 电话").append(reportDTO.getMobile()).append("带看").append(reportDTO.getPropertyName())
            .append("项目现客户已成功到访，具体到访情况请联系市场专员，也请及时跟进好客户意向和进度，双方密切沟通以免客户流失，祝您开单！");
        messageEntity = new MessageEntity();
        messageEntity.setType(2);
        messageEntity.setTitle(title);
        messageEntity.setContent(content.toString());
        insert(messageEntity);
        MessageUserEntity messageUserEntity = new MessageUserEntity();
        messageUserEntity.setMessageId(messageEntity.getId());
        messageUserEntity.setUnionId(reportDTO.getUnionId());
        messageUserService.insert(messageUserEntity);
    }

    @Async
    @Override
    public void sendAuthMessage(Long unionId, String tel) {
        MessageEntity messageEntity;
        String title = "认证";
        StringBuilder content = new StringBuilder();
        content.append("微房宝提醒您，您已通过认证成为微房宝合作经纪人，您给我报备我还您成交，海量认证房源等您掘金。感谢您的支持，如需帮助请咨询").append(tel);
        messageEntity = new MessageEntity();
        messageEntity.setType(1);
        messageEntity.setTitle(title);
        messageEntity.setContent(content.toString());
        insert(messageEntity);
        MessageUserEntity messageUserEntity = new MessageUserEntity();
        messageUserEntity.setMessageId(messageEntity.getId());
        messageUserEntity.setUnionId(unionId);
        messageUserService.insert(messageUserEntity);
    }

    @Async
    @Override
    public void sendInvalidMessage(List<ReportListDTO> dtoList) {
        if (CollectionUtils.isNotEmpty(dtoList)) {
            MessageEntity messageEntity;
            MessageUserEntity messageUserEntity;
            String title = "报备过期";
            StringBuilder content;
            for (ReportListDTO e : dtoList) {
                content = new StringBuilder();
                content.append("微房宝提醒您，您报备的").append(e.getCustomerName()).append(" 电话").append(e.getMobile()).append("带看").append(e.getPropertyName())
                    .append("项目已超过报备保护期失效，请及时跟进好客户进度在必要时重新报备，以免客户流失，祝您开单！");
                messageEntity = new MessageEntity();
                messageEntity.setType(2);
                messageEntity.setTitle(title);
                messageEntity.setContent(content.toString());
                insert(messageEntity);
                messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(messageEntity.getId());
                messageUserEntity.setUnionId(e.getUnionId());
                messageUserService.insert(messageUserEntity);
            }
        }
    }

    @Async
    @Override
    public void sendWillInvalidMessage(List<ReportListDTO> willInvalidList) {
        if (CollectionUtils.isNotEmpty(willInvalidList)) {
            MessageEntity messageEntity;
            MessageUserEntity messageUserEntity;
            String title = "报备即将过期";
            StringBuilder content;
            for (ReportListDTO e : willInvalidList) {
                content = new StringBuilder();
                content.append("微房宝提醒您，您报备的").append(e.getCustomerName()).append(" 电话").append(e.getMobile()).append("带看").append(e.getPropertyName())
                    .append("项目距离报备有效期还有最后2小时，请及时跟进好客户进度在规定时效内带看，以免客户流失，祝您开单！");
                messageEntity = new MessageEntity();
                messageEntity.setType(2);
                messageEntity.setTitle(title);
                messageEntity.setContent(content.toString());
                insert(messageEntity);
                messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(messageEntity.getId());
                messageUserEntity.setUnionId(e.getUnionId());
                messageUserService.insert(messageUserEntity);
            }
        }
    }

    @Async
    @Override
    public void sendPropertyMessage(List<Long> unionIdList, PropertyDTO propertyDTO, String tel) {
        if (CollectionUtils.isNotEmpty(unionIdList)) {
            MessageEntity messageEntity = new MessageEntity();
            MessageUserEntity messageUserEntity;
            String title = "新进项目";
            StringBuilder content = new StringBuilder();
            content.append("微房宝提醒您，微房宝新进项目").append(propertyDTO.getPropertyName()).append("，核心地段优质产品，面积段").append(propertyDTO.getMinBuildArea()).append("-").append(propertyDTO.getMaxBuildArea())
                .append(",均价").append(propertyDTO.getUnitPrice()).append("，佣金").append(propertyDTO.getCommission()).append("，火速结佣；详询专属市场专员或来电咨询").append(tel);
            messageEntity.setType(1);
            messageEntity.setTitle(title);
            messageEntity.setContent(content.toString());
            insert(messageEntity);
            List<MessageUserEntity> list = new ArrayList<>();
            for (Long e : unionIdList) {
                messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(messageEntity.getId());
                messageUserEntity.setUnionId(e);
                list.add(messageUserEntity);
            }
            messageUserService.insertBatch(list);
        }
    }
}