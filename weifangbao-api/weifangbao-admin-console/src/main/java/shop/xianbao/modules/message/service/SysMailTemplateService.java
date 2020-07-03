/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.service;

import shop.xianbao.common.entity.SysMailTemplateEntity;
import shop.xianbao.common.service.CrudService;
import shop.xianbao.modules.message.dto.SysMailTemplateDTO;

/**
 * 邮件模板
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysMailTemplateService extends CrudService<SysMailTemplateEntity, SysMailTemplateDTO> {

    /**
     * 发送邮件
     * @param id           邮件模板ID
     * @param mailTo       收件人
     * @param mailCc       抄送
     * @param params       模板参数
     */
    boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception;
}