package shop.xianbao.modules.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 消息推送表
 * 列表DTO
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Data
@ApiModel(value = "消息推送表-列表")
public class PushMessageListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "id")
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