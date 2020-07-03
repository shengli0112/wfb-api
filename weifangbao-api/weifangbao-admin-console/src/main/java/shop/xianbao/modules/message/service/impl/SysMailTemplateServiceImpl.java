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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.entity.SysMailTemplateEntity;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.CrudServiceImpl;
import shop.xianbao.modules.message.dao.SysMailTemplateDao;
import shop.xianbao.modules.message.dto.SysMailTemplateDTO;
import shop.xianbao.modules.message.email.EmailUtils;
import shop.xianbao.modules.message.service.SysMailTemplateService;

import java.util.Map;

@Service
public class SysMailTemplateServiceImpl extends CrudServiceImpl<SysMailTemplateDao, SysMailTemplateEntity, SysMailTemplateDTO> implements SysMailTemplateService {
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public QueryWrapper<SysMailTemplateEntity> getWrapper(Map<String, Object> params) {
        String name = (String)params.get("name");

        QueryWrapper<SysMailTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);

        return wrapper;
    }

    @Override
    public boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception {
        Map<String, Object> map = null;
        try {
            if (StringUtils.isNotEmpty(params)) {
                map = JSON.parseObject(params, Map.class);
            }
        } catch (Exception e) {
            throw new XianbaoException(ErrorCode.JSON_FORMAT_ERROR);
        }
        String[] to = new String[] {mailTo};
        String[] cc = StringUtils.isBlank(mailCc) ? null : new String[] {mailCc};

        return emailUtils.sendMail(id, to, cc, map);
    }
}
