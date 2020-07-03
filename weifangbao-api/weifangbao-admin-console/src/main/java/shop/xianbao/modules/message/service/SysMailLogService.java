/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.service;

import shop.xianbao.common.entity.SysMailLogEntity;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.message.dto.SysMailLogDTO;

import java.util.Map;

/**
 * 邮件发送记录
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysMailLogService extends BaseService<SysMailLogEntity> {

    PageData<SysMailLogDTO> page(Map<String, Object> params);

    /**
     * 保存邮件发送记录
     * @param templateId  模板ID
     * @param from        发送者
     * @param to          收件人
     * @param cc          抄送
     * @param subject     主题
     * @param content     邮件正文
     * @param status      状态
     */
    void save(Long templateId, String from, String[] to, String[] cc, String subject, String content, Integer status);
}

