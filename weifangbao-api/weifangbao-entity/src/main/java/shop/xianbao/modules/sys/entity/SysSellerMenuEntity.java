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
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 菜单管理
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_seller_menu")
public class SysSellerMenuEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	private Long pid;
	/**
	 * 菜单名称
	 */
	@TableField(exist = false)
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权(多个用逗号分隔，如：sys:user:list,sys:user:saveGoods)
	 */
	private String permissions;
	/**
	 * 类型   0：菜单   1：按钮
	 */
	private Integer type;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	private Integer sort;
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
	/**
	 * 上级菜单名称
	 */
	@TableField(exist = false)
	private String parentName;

}