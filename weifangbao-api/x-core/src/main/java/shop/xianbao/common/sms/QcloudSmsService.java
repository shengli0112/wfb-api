/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.sms;

import cn.hutool.core.map.MapUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.SmsService;
import shop.xianbao.common.utils.SpringContextUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 腾讯云短信服务
 *
 * @author Tim tim@ruitukeji.com
 */
public class QcloudSmsService extends AbstractSmsService {
    public QcloudSmsService(SmsConfig config){
        this.config = config;
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params) {
        this.sendSms(mobile, params, config.getQcloudSignName(), config.getQcloudTemplateId());
    }
    
    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params, String template) {
        this.sendSms(mobile, params, config.getQcloudSignName(), template);
    }
    
    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params, String signName, String template) {
        SmsSingleSender sender = new SmsSingleSender(config.getQcloudAppId(), config.getQcloudAppKey());

        //短信参数
        ArrayList<String> paramsList = new ArrayList<>();
        if(MapUtil.isNotEmpty(params)){
            for(String value : params.values()){
                paramsList.add(value);
            }
        }
        SmsSingleSenderResult result;
        try {
            result = sender.sendWithParam("86", mobile, Integer.parseInt(template), paramsList, signName, null, null);
        } catch (Exception e) {
            throw new XianbaoException(ErrorCode.SEND_SMS_ERROR, e, "");
        }

        int status = Constant.SUCCESS;
        if(result.result != 0){
            status = Constant.FAIL;
        }

        //保存短信记录
        SmsService sysSmsService = SpringContextUtils.getBean(SmsService.class);
        sysSmsService.save(Constant.SmsService.QCLOUD.getValue(), mobile, params, status);

        if(status == Constant.FAIL){
            throw new XianbaoException(ErrorCode.SEND_SMS_ERROR, result.errMsg);
        }
    }
}
