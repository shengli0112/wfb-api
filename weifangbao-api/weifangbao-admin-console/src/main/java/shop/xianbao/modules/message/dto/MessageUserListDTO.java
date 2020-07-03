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

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "消息id")
    private Long messageId;

    @ApiModelProperty(value = "用户id")
    private Long unionId;

    @ApiModelProperty(value = "是否已读  0否 1是")
    private Integer isRead;

    @ApiModelProperty(value = "阅读时间")
    private Date readTime;

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