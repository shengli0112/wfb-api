package shop.xianbao.modules.pay.service;

import java.util.Date;

/**
 * @program: WxService
 * @description:
 * @author: yh
 * @create: 2019-10-22 16:17
 **/
public interface WxService {

    /**
     * 发送微信下单通知模板消息
     * 模版ID：ep4Tuim__0S_odG-OQubw8iVIlnlQ7smOT6wB_uEZk4
     * 标题：订单下单提醒
     * 详细内容
     * {{first.DATA}}
     * 订单号：{{keyword1.DATA}}
     * 发货时间：{{keyword2.DATA}}
     * {{remark.DATA}}
     *
     * @param openId
     * @param content
     * @param orderSn
     * @param deliverTime
     * @param remark
     */
    void sendWxPlaceOrderMessage(String openId, String content, String orderSn, Date deliverTime, String remark);

    /**
     * 发送微信发货通知模板消息
     * 模版ID：WxS4Txi4SsHbslYuYdgLXZfjkUCYIU9z7OvB_y0-mWU
     * 标题：订单发货提醒
     * 详细内容
     * {{first.DATA}}
     * 订单号：{{keyword1.DATA}}
     * 发货时间：{{keyword2.DATA}}
     * {{remark.DATA}}
     *
     * @param openId
     * @param content
     * @param orderSn
     * @param deliverTime
     * @param remark
     */
    void sendWxDeliverMessage(String openId, String content, String orderSn, Date deliverTime, String remark);
}
