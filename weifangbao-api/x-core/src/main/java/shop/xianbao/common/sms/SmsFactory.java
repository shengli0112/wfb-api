/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.sms;

import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.service.ParamsService;
import shop.xianbao.common.utils.SpringContextUtils;

/**
 * 短信Factory
 *
 * @author Tim tim@ruitukeji.com
 */
public class SmsFactory {
    private static ParamsService sysParamsService;

    static {
        SmsFactory.sysParamsService = SpringContextUtils.getBean(ParamsService.class);
    }

    public static AbstractSmsService build() {
        //获取短信配置信息
        SmsConfig config = sysParamsService.getValueObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);

        if (config.getPlatform() == Constant.SmsService.ALIYUN.getValue()) {
            return new AliyunSmsService(config);
        } else if (config.getPlatform() == Constant.SmsService.QCLOUD.getValue()) {
            return new QcloudSmsService(config);
        } else if (config.getPlatform() == Constant.SmsService.JUHE.getValue()) {
            return new JuheSmsService(config);
        }

        return null;
    }
}
