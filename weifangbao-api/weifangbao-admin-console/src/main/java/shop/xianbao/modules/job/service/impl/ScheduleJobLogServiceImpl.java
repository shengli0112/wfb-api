/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.job.dao.ScheduleJobLogDao;
import shop.xianbao.modules.job.dto.ScheduleJobLogDTO;
import shop.xianbao.modules.job.entity.ScheduleJobLogEntity;
import shop.xianbao.modules.job.service.ScheduleJobLogService;

import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
        IPage<ScheduleJobLogEntity> page = baseDao.selectPage(getPage(params, Constant.CREATE_DATE, false), getWrapper(params));
        return getPageData(page, ScheduleJobLogDTO.class);
    }

    private QueryWrapper<ScheduleJobLogEntity> getWrapper(Map<String, Object> params) {
        String jobId = (String)params.get("jobId");

        QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(jobId), "job_id", jobId);

        return wrapper;
    }

    @Override
    public ScheduleJobLogDTO get(Long id) {
        ScheduleJobLogEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, ScheduleJobLogDTO.class);
    }

}