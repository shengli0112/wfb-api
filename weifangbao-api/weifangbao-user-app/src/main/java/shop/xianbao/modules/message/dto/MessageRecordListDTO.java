package shop.xianbao.modules.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 消息记录表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-07
 */
@Data
@ApiModel(value = "消息记录表-列表")
public class MessageRecordListDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

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

}