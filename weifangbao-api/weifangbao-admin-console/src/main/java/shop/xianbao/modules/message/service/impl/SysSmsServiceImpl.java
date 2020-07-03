/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */
package shop.xianbao.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.entity.SysSmsEntity;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.SmsServiceImpl;
import shop.xianbao.modules.message.dto.SysSmsDTO;
import shop.xianbao.modules.message.service.SysSmsService;

import java.util.Map;

@Service
public class SysSmsServiceImpl extends SmsServiceImpl implements SysSmsService {

    @Override
    public PageData<SysSmsDTO> page(Map<String, Object> params) {
        IPage<SysSmsEntity> page = baseDao.selectPage(getPage(params, Constant.CREATE_DATE, false), getWrapper(params));
        return getPageData(page, SysSmsDTO.class);
    }

    private QueryWrapper<SysSmsEntity> getWrapper(Map<String, Object> params) {
        String mobile = (String)params.get("mobile");
        String status = (String)params.get("status");

        QueryWrapper<SysSmsEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(mobile), "mobile", mobile);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);

        return wrapper;
    }

}
