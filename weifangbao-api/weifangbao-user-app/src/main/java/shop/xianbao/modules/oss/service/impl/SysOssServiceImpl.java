/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.oss.dao.SysOssDao;
import shop.xianbao.modules.oss.entity.SysOssEntity;
import shop.xianbao.modules.oss.service.SysOssService;

import java.util.Map;

@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageData<SysOssEntity> page(Map<String, Object> params) {
        IPage<SysOssEntity> page = baseDao.selectPage(getPage(params, Constant.CREATE_DATE, false), new QueryWrapper<>());
        return getPageData(page, SysOssEntity.class);
    }
}
