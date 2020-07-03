/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */
package shop.xianbao.common.service.impl;

import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Service;
import shop.xianbao.common.dao.SysSmsDao;
import shop.xianbao.common.entity.SysSmsEntity;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.SmsService;
import shop.xianbao.common.sms.AbstractSmsService;
import shop.xianbao.common.sms.SmsFactory;

import java.util.LinkedHashMap;

@Service
public abstract class SmsServiceImpl extends BaseServiceImpl<SysSmsDao, SysSmsEntity> implements SmsService {

    @Override
    public void send(String mobile, LinkedHashMap<String, String> map) {
        //短信服务
        AbstractSmsService service = SmsFactory.build();
        if (service == null) {
            throw new XianbaoException(ErrorCode.SMS_CONFIG);
        }

        //发送短信
        service.sendSms(mobile, map);
    }

    @Override
    public void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status) {
        SysSmsEntity sms = new SysSmsEntity();
        sms.setPlatform(platform);
        sms.setMobile(mobile);

        //设置短信参数
        if (MapUtil.isNotEmpty(params)) {
            int index = 1;
            for (String value : params.values()) {
                if (index == 1) {
                    sms.setParams1(value);
                } else if (index == 2) {
                    sms.setParams2(value);
                } else if (index == 3) {
                    sms.setParams3(value);
                } else if (index == 4) {
                    sms.setParams4(value);
                }
                index++;
            }
        }

        sms.setStatus(status);

        this.insert(sms);
    }
}
