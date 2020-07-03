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
import shop.xianbao.modules.job.dto.ScheduleJobDTO;
import shop.xianbao.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Tim tim@ruitukeji.com
 */
public interface ScheduleJobService extends BaseService<ScheduleJobEntity> {

    PageData<ScheduleJobDTO> page(Map<String, Object> params);

    ScheduleJobDTO get(Long id);

    /**
     * 保存定时任务
     */
    void save(ScheduleJobDTO dto);

    /**
     * 更新定时任务
     */
    void update(ScheduleJobDTO dto);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] ids);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] ids, int status);

    /**
     * 立即执行
     */
    void run(Long[] ids);

    /**
     * 暂停运行
     */
    void pause(Long[] ids);

    /**
     * 恢复运行
     */
    void resume(Long[] ids);
}
