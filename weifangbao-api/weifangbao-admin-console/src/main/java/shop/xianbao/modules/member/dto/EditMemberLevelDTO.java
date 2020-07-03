package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 会员等级
 *
 * @author Mark shc@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Data
@ApiModel(value = "会员等级")
public class EditMemberLevelDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "等级id")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    private Integer storeId;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "累计积分")
    private Integer minIntegral;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal goodsDiscount;

    @ApiModelProperty(value = "等级描述")
    private String description;

    @ApiModelProperty(value = "是否是默认")
    private Integer isDefault;

    @ApiModelProperty(value = "消费额度")
    private Integer quota;

    @ApiModelProperty(value = "升级条件  1.累计积分 2.消费额度 3.同时满足")
    private Integer upgrade;

    @ApiModelProperty(value = "1.或 2. 且")
    private Integer relation;

}