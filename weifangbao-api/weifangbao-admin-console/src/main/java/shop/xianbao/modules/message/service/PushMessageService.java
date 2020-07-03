package shop.xianbao.modules.message.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.message.dto.PushMessageDTO;
import shop.xianbao.modules.message.dto.PushMessageListDTO;
import shop.xianbao.modules.message.entity.PushMessageEntity;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
public interface PushMessageService extends XianbaoCrudService<PushMessageEntity, PushMessageDTO, PushMessageListDTO> {
    //
    /**
     * 极光推送配置KEY-用户端
     */
    String JPUSH_MEMBER_REDIS_KEY = "JPUSH_MEMBER";
    /**
     * 极光推送配置KEY-商家端
     */
    String JPUSH_SELLER_REDIS_KEY = "JPUSH_SELLER";

    // 业务类型  1=新订单 2=接单超时 3=接单 4=配送 5=确认收货

    /**
     * 业务类型 1=新订单
     */
    int BIZ_TYPE_NEW_ORDER = 1;
    /**
     * 业务类型 2=接单超时
     */
    int BIZ_TYPE_RECEIVE_TIMEOUT = 2;

    /**
     * 业务用户类型  1=member
     */
    int USER_TYPE_MEMBER = 1;
    /**
     * 业务用户类型  2=seller
     */
    int USER_TYPE_SELLER = 2;

    /**
     * 推送平台 all 全部平台
     */
    String PLATFORM_ALL = "all";
    /**
     * 业务用户类型 app: ios+android
     */
    String PLATFORM_APP = "app";
    /**
     * 业务用户类型 android
     */
    String PLATFORM_ANDROID = "android";
    /**
     * 业务用户类型 ios
     */
    String PLATFORM_IOS = "ios";
    /**
     * 业务用户类型 mobile
     */
    String PLATFORM_MOBILE = "mobile";

    boolean pushMessage(PushMessageEntity entity);
}