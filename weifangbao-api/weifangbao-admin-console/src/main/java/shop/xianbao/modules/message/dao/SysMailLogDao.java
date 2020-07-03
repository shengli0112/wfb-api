/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.common.entity.SysMailLogEntity;

/**
 * 邮件发送记录
 *
 * @author Tim tim@ruitukeji.com
 */
@Mapper
public interface SysMailLogDao extends BaseDao<SysMailLogEntity> {

}
