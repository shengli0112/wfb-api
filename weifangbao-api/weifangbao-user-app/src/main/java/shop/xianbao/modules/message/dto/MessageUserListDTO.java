package shop.xianbao.modules.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户消息表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-10-22
 */
@Data
@ApiModel(value = "用户消息表-列表")
public class MessageUserListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息类型 1平台消息  2业务消息")
    private Integer type;

    @ApiModelProperty(value = "消息标题")
    private String title;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "消息链接")
    private String url;

    @ApiModelProperty(value = "业务id")
    private Long targetId;

    @ApiModelProperty(value = "用户消息id")
    private Long messageUserId;

    @ApiModelProperty(value = "消息id")
    private Long messageId;

    @ApiModelProperty(value = "用户id")
    private Long unionId;

    @ApiModelProperty(value = "是否已读  0否 1是")
    private Integer isRead;

    @ApiModelProperty(value = "阅读时间")
    private Date readTime;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

}