/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 业务基础实体类，所有实体都需要继承
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
public abstract class XianBaoBaseEntity  extends  BaseEntity {
    
    /**
     * 排序字段 小值在前
     */
    private Integer sort;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;

}