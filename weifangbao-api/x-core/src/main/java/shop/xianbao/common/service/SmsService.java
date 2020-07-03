/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service;

import shop.xianbao.common.entity.SysSmsEntity;

import java.util.LinkedHashMap;

/**
 * 短信
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SmsService extends BaseService<SysSmsEntity> {

    /**
     * 发送短信
     * @param mobile   手机号
     * @param params   短信参数
     */
    void send(String mobile, LinkedHashMap<String, String> params);

    /**
     * 保存短信发送记录
     * @param platform   平台
     * @param mobile   手机号
     * @param params   短信参数
     * @param status   发送状态
     */
    void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status);
}

