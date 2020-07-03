/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.job.task;

/**
 * 定时任务接口，所有定时任务都要实现该接口
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public interface ITask {

    /**
     * 执行定时任务接口
     *
     * @param params 参数，多参数使用JSON数据
     */
    void run(String params);
}