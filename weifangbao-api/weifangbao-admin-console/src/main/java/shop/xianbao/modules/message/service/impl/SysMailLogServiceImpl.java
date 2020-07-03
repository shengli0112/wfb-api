/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.entity.SysMailLogEntity;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.message.dao.SysMailLogDao;
import shop.xianbao.modules.message.dto.SysMailLogDTO;
import shop.xianbao.modules.message.service.SysMailLogService;

import java.util.Map;

@Service
public class SysMailLogServiceImpl extends BaseServiceImpl<SysMailLogDao, SysMailLogEntity> implements SysMailLogService {

    @Override
    public PageData<SysMailLogDTO> page(Map<String, Object> params) {
        IPage<SysMailLogEntity> page = baseDao.selectPage(getPage(params, Constant.CREATE_DATE, false), getWrapper(params));
        return getPageData(page, SysMailLogDTO.class);
    }

    private QueryWrapper<SysMailLogEntity> getWrapper(Map<String, Object> params) {
        String templateId = (String)params.get("templateId");
        String mailTo = (String)params.get("mailTo");
        String status = (String)params.get("status");

        QueryWrapper<SysMailLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(templateId), "template_id", templateId);
        wrapper.like(StringUtils.isNotBlank(mailTo), "mail_to", mailTo);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);

        return wrapper;
    }

    @Override
    public void save(Long templateId, String from, String[] to, String[] cc, String subject, String content, Integer status) {
        SysMailLogEntity log = new SysMailLogEntity();
        log.setTemplateId(templateId);
        log.setMailFrom(from);
        log.setMailTo(JSON.toJSONString(to));
        if (cc != null) {
            log.setMailCc(JSON.toJSONString(cc));
        }
        log.setSubject(subject);
        log.setContent(content);
        log.setStatus(status);
        this.insert(log);
    }

}