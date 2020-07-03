/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.job.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.job.dto.ScheduleJobLogDTO;
import shop.xianbao.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Tim tim@ruitukeji.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

    PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

    ScheduleJobLogDTO get(Long id);
}
