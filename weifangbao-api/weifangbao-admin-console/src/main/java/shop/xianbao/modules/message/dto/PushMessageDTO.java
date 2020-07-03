package shop.xianbao.modules.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 消息推送表
 * 新增/详情/修改接口的DTO
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Data
@ApiModel(value = "消息推送表")
public class PushMessageDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "业务用户类型  1=member 2=seller")
    private Integer userType;

    @ApiModelProperty(value = "业务用户id")
    private Long userId;

    @ApiModelProperty(value = "推送平台  all,app,ios,android,mobile  all：全部平台  app: ios+android  mobile: 手机")
    private String platform;

    @ApiModelProperty(value = "推送目标 设备标识/手机号  如果要发广播（全部设备），则直接填写 “all”。")
    private String receiver;

    @ApiModelProperty(value = "消息标题")
    private String msgTitle;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "附加字段")
    private String extras;

    @ApiModelProperty(value = "业务参数")
    private String params;

    @ApiModelProperty(value = "期望推送时间")
    private Integer pushTime;

    @ApiModelProperty(value = "是否已推送 0未发送 1已发送")
    private Integer isPushed;

    @ApiModelProperty(value = "排序字段 小值在前")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}