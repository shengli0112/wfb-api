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
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.message.dao.MessageDao;
import shop.xianbao.modules.message.dto.MessageDTO;
import shop.xianbao.modules.message.dto.MessageListDTO;
import shop.xianbao.modules.message.entity.MessageEntity;
import shop.xianbao.modules.message.entity.MessageUserEntity;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.message.service.MessageUserService;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;

import java.util.*;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageDao, MessageEntity> implements MessageService {

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
        if (entity != null) {
            return ConvertUtils.sourceToTarget(entity, MessageDTO.class);
        }
        return null;
    }

    @Override
    public Result messageInfo(Long messageId, MemberDataEntity user) {
        MessageDTO messageDTO = get(messageId);
        if (messageDTO != null) {
            UpdateWrapper<MessageUserEntity> wrapper = new UpdateWrapper<>();
            wrapper.set("is_read", 1);
            wrapper.set("read_time", new Date());
            wrapper.eq("union_id", user.getUnionId());
            wrapper.eq("message_id", messageId);
            messageUserService.update(new MessageUserEntity(), wrapper);
        }
        Result<Object> result = new Result<>();
        return result.ok(messageDTO);
    }

    @Override
    public boolean add(MessageDTO dto) {
        MessageEntity entity = ConvertUtils.sourceToTarget(dto, MessageEntity.class);

        return insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(MessageDTO dto, List<Long> unionIds) {
        MessageEntity entity = ConvertUtils.sourceToTarget(dto, MessageEntity.class);
        insert(entity);

        //保存用户消息
        if (CollectionUtils.isNotEmpty(unionIds)) {
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
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Async
    @Override
    public void sendReportMessage(Long unionId, List<ReportListDTO> smsList) {
        if (CollectionUtils.isNotEmpty(smsList)) {
            MessageEntity messageEntity;
            String title = "楼盘报备";
            StringBuilder content;
            for (ReportListDTO e : smsList) {
                content = new StringBuilder();
                content.append("微房宝提醒您，您报备的").append(e.getCustomerName()).append(" 电话").append(e.getMobile()).append("带看").append(e.getPropertyName()).append("项目已成功报备，报备有效期24小时，请在规定时效内带看，祝您开单！");
                messageEntity = new MessageEntity();
                messageEntity.setType(2);
                messageEntity.setTitle(title);
                messageEntity.setContent(content.toString());
                insert(messageEntity);
                MessageUserEntity messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(messageEntity.getId());
                messageUserEntity.setUnionId(unionId);
                messageUserService.insert(messageUserEntity);
            }
        }
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

    /**
     * 发送短信给楼盘管理人
     */
    @Override
    public
    void sendReportMessageToMng(List<ReportListDTO> reportList){
        if (CollectionUtils.isNotEmpty(reportList)) {

            MessageEntity messageEntity;
            String title = "报备成功提醒楼盘管理人";
            StringBuilder content;
            for (ReportListDTO e : reportList) {
                content = new StringBuilder();
                // 微房宝楼盘管理人请注意，新增报备来啦，您的#project#项目 已被 #respectname#先生\女士 电话：#mobile# 已成功报备，请及时跟进.
                // 微房宝楼盘管理人请注意，新增报备来啦，（经纪人#brokerName#报备客户#customerName#，电话#customerMobile#），请及时跟进！
                content.append("微房宝楼盘管理人请注意，新增报备来啦，（经纪人").append(e.getNickname()).append("报备客户").append(e.getCustomerName()).append("，电话").append(e.getMobile()).append("），请及时跟进！");
                messageEntity = new MessageEntity();
                messageEntity.setType(2);
                messageEntity.setTitle(title);
                messageEntity.setContent(content.toString());
                insert(messageEntity);
                MessageUserEntity messageUserEntity = new MessageUserEntity();
                messageUserEntity.setMessageId(messageEntity.getId());
                messageUserEntity.setUnionId(e.getUnionId());
                messageUserService.insert(messageUserEntity);
            }
        }
    }
}