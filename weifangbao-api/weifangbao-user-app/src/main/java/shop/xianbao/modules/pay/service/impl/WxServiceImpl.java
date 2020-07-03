package shop.xianbao.modules.pay.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import shop.xianbao.modules.pay.config.wx.WxMpUtils;
import shop.xianbao.modules.pay.service.WxService;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;
import shop.xianbao.modules.sys.service.SysParamsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: WxServiceImpl
 * @description:
 * @author: yh
 * @create: 2019-10-22 16:18
 **/
@Service
public class WxServiceImpl implements WxService {

    @Autowired
    private PayTypeService payTypeService;

    private WxMpService wxMpService;

    @Autowired
    private SysParamsService sysParamsService;

    /**
     * 发送微信模板消息
     *
     * @param wxMpTemplateMessage
     */
    private void sendWxTemplateMessage(WxMpTemplateMessage wxMpTemplateMessage) {
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("wechat_pay", "JSAPI_FWH");
        if (Objects.equals(payTypeEntity, null)) {
            return;
        }
        wxMpService = WxMpUtils.getWxMpService(payTypeEntity.getConfig());
        WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();
        try {
            String s = templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWxPlaceOrderMessage(String openId, String content, String orderSn, Date deliverTime, String remark) {
        List<WxMpTemplateData> templateDataList = new ArrayList<>();

        WxMpTemplateData first = new WxMpTemplateData("first", content, "");
        templateDataList.add(first);

        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", orderSn, "");
        templateDataList.add(keyword1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", remark, "");
        templateDataList.add(keyword2);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", "下单时间:" + dateFormat.format(deliverTime), "");
        templateDataList.add(remarkData);
        String wxTemplateDeliverId = sysParamsService.getValue("WX_TEMPLATE_PLACE_ORDER");
        if (StringUtils.isEmpty(wxTemplateDeliverId)) {
            return;
        }
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage(openId, wxTemplateDeliverId, "", null, templateDataList);
        sendWxTemplateMessage(wxMpTemplateMessage);
    }

    @Override
    @Async
    public void sendWxDeliverMessage(String openId, String content, String orderSn, Date deliverTime, String remark) {
        List<WxMpTemplateData> templateDataList = new ArrayList<>();

        WxMpTemplateData first = new WxMpTemplateData("first", content, "");
        templateDataList.add(first);

        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", orderSn, "");
        templateDataList.add(keyword1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", dateFormat.format(deliverTime), "");
        templateDataList.add(keyword2);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, "");
        templateDataList.add(remarkData);
        String wxTemplateDeliverId = sysParamsService.getValue("WX_TEMPLATE_DELIVER");
        if (StringUtils.isEmpty(wxTemplateDeliverId)) {
            return;
        }
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage(openId, wxTemplateDeliverId, "", null, templateDataList);
        sendWxTemplateMessage(wxMpTemplateMessage);
    }
}
