package shop.xianbao.modules.message.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.message.config.JPushParam;
import shop.xianbao.modules.message.dao.PushMessageDao;
import shop.xianbao.modules.message.dto.PushMessageDTO;
import shop.xianbao.modules.message.dto.PushMessageListDTO;
import shop.xianbao.modules.message.entity.PushMessageEntity;
import shop.xianbao.modules.message.service.PushMessageService;
import shop.xianbao.modules.sys.service.SysParamsService;

import java.util.Map;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Service
public class PushMessageServiceImpl extends XianbaoCrudServiceImpl<PushMessageDao, PushMessageEntity, PushMessageDTO, PushMessageListDTO> implements PushMessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysParamsService sysParamsService;

    @Override
    public QueryWrapper<PushMessageEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<PushMessageEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public boolean pushMessage(PushMessageEntity entity) {
        String platform = entity.getPlatform();
        Platform jgPlatform;
        switch (platform) {
            case "all":
            case "app":
                jgPlatform = Platform.all();
                break;
            case "ios":
                jgPlatform = Platform.ios();
                break;
            case "android":
                jgPlatform = Platform.android();
                break;
            default:
                jgPlatform = null;
        }

        if (jgPlatform == null) {
            return false;
        }
        Audience audience;
        if ("all".equals(entity.getReceiver())) {
            audience = Audience.all();
        } else {
            audience = Audience.registrationId(StringUtils.split(entity.getReceiver(), ","));
        }

        boolean pushOk = jpushMsg(entity.getUserType(), jgPlatform, entity.getMsgTitle(), audience);
        PushMessageEntity newEntity = new PushMessageEntity();
        newEntity.setId(entity.getId());
        newEntity.setIsPushed(pushOk ? 1 : 0);
        newEntity.setPushTimes(entity.getPushTimes() + 1);
        updateById(newEntity);
        return true;
    }

    private boolean jpushMsg(int userType, Platform platform, String title, Audience audience) {

        JPushParam jPushParam = null;
        if (userType == USER_TYPE_MEMBER) {
            jPushParam = sysParamsService.getValueObject(JPUSH_MEMBER_REDIS_KEY, JPushParam.class);
        }
        if (userType == USER_TYPE_SELLER) {
            jPushParam = sysParamsService.getValueObject(JPUSH_SELLER_REDIS_KEY, JPushParam.class);
        }
        if (jPushParam == null) {
            logger.error("获取极光推送配置参数失败");
            return false;
        }
        JPushClient jpushClient = new JPushClient(jPushParam.getMasterSecret(), jPushParam.getAppKey(), null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.

        PushPayload payload = buildPushObject(platform, title, audience);

        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("极光推送 Got result - " + result);
            return result.statusCode == PushResult.ERROR_CODE_OK;
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later " + e.getMessage());
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request" + e.getMessage());
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
        return false;
    }

    private static PushPayload buildPushObject(Platform platform, String title, Audience audience) {
        //  alert field is needed. It can be empty string.;
        return PushPayload.newBuilder().setPlatform(platform).setAudience(audience).setNotification(Notification.alert(title)).build();
    }
}