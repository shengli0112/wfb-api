package shop.xianbao.common.service.impl;

import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.SmsMessageService;
import shop.xianbao.common.sms.AbstractSmsService;
import shop.xianbao.common.sms.SmsFactory;

import java.util.LinkedHashMap;

/**
 * @program: MessageSmsService
 * @description: 发送短信消息
 * @author: yh
 * @create: 2019-11-28 17:25
 **/
public abstract class SmsMessageServiceImpl implements SmsMessageService {

    @Override
    public void sendSmsMessage(String mobile, LinkedHashMap<String, String> params, SmsMessageService.OptEnum opt) {
        if (opt == null) {
            return;
        }
        //短信服务
        AbstractSmsService service = SmsFactory.build();
        if (service == null) {
            throw new XianbaoException(ErrorCode.SMS_CONFIG);
        }

        //发送短信
        service.sendSms(mobile, params, opt.template());
    }

    @Override
    public void sendSmsMessage(String mobile, String customerName, String customerMobile, String propertyName, SmsMessageService.OptEnum opt) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("respectname", customerName);
        params.put("mobile", customerMobile);
        params.put("project", propertyName);
        sendSmsMessage(mobile, params, opt);
    }

    @Override
    public void sendSmsMessage(String mobile, String propertyName, String area, String price, String commission, String param,String tel, SmsMessageService.OptEnum opt) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("project", propertyName);
        params.put("area", area);
        params.put("price", price);
        params.put("commission", commission);
        params.put("param", param);
        params.put("tel", tel);
        sendSmsMessage(mobile, params, opt);
    }
}
