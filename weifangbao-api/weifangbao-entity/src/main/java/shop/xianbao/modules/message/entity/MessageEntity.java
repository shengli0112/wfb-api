package shop.xianbao.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_message")
public class MessageEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 消息类型 1平台消息  2业务消息
     */
	private Integer type;
    /**
     * 消息标题
     */
	private String title;
    /**
     * 消息内容
     */
	private String content;
    /**
     * 图片
     */
	private String image;
    /**
     * 消息链接
     */
	private String url;
    /**
     * 业务id
     */
	private Long targetId;
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