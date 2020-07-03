package shop.xianbao.modules.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@Data
@ApiModel(value = "消息记录表")
public class MessageRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "发送人id")
    private Long senderId;

    @ApiModelProperty(value = "接收人id")
    private Long receiverId;

    @ApiModelProperty(value = "发送内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "更新者id")
    private Long updater;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}