/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.service;

import shop.xianbao.common.entity.SysSmsEntity;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.message.dto.SysSmsDTO;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysSmsService extends BaseService<SysSmsEntity> {

    PageData<SysSmsDTO> page(Map<String, Object> params);

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param params 短信参数
     */
    void send(String mobile, LinkedHashMap<String, String> params);

    /**
     * 保存短信发送记录
     *
     * @param platform 平台
     * @param mobile   手机号
     * @param params   短信参数
     * @param status   发送状态
     */
    void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status);
}

