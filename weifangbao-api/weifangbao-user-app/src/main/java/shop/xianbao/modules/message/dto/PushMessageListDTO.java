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

    /**
     * 业务类型  1=新订单 2=接单超时 3=接单 4=配送 5=确认收货
     */
    private Integer bizType;

    /**
     * 业务id
     */
    private Long bizId;

    @ApiModelProperty(value = "消息标题")
    private String msgTitle;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "附加字段")
    private String extras;

    @ApiModelProperty(value = "业务参数")
    private String params;

    @ApiModelProperty(value = "推送时间")
    private Integer pushTime;

    @ApiModelProperty(value = "推送时间")
    public Date getPushTime() {
        return new Date(this.pushTime * 1000L);
    }

}