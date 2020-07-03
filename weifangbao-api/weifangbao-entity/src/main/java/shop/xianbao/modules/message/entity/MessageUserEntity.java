package shop.xianbao.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_message_user")
public class MessageUserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
	private Long messageId;
    /**
     * 用户id
     */
	private Long unionId;
    /**
     * 是否已读  0否 1是
     */
	private Integer isRead;
    /**
     * 阅读时间
     */
	private Date readTime;
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