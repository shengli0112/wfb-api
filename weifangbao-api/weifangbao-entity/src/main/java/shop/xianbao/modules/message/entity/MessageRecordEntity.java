package shop.xianbao.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_message_record")
public class MessageRecordEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 发送人id
     */
	private Long senderId;
    /**
     * 接收人id
     */
	private Long receiverId;
    /**
     * 发送内容
     */
	private String content;
    /**
     * 发送时间
     */
	private Date sendTime;
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