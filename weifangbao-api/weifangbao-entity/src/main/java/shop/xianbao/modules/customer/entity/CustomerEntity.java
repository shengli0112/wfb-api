package shop.xianbao.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_customer")
public class CustomerEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long unionId;

	/**
	 * 头像
	 */
	private String avatar;

    /**
     * 姓
     */
    private String surname;

    /**
     * 名
     */
    private String name;

    /**
     * 姓名首拼
     */
    private String firstSpelling;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 性别 0保密 1男 2女
     */
    private Integer gender;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否星标 0否 1是
     */
    private Integer isMarked;

    /**
     * 排序字段 小值在前
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新者id
     */
    private Long updater;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}