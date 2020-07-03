/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 角色
 * 
 * @author Tim tim@ruitukeji.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_seller_role")
public class SysSellerRoleEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@TableField(value="`name`")
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 部门ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long deptId;
	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
}
