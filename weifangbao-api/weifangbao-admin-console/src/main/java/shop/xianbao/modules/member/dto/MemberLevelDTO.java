package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

/**
 * 会员等级
 * 新增/详情/修改接口的DTO
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@ApiModel(value = "会员等级")
public class MemberLevelDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    // 以下注释看完删除:
    // 根据自己的业务需求加上 hibernate validator 注解
    // 校验字符类型用 --> @NotBlank(message = "{xxx.xxx.xx}", groups = {AddGroup.class, UpdateGroup.class})
    // 校验数字类型用 --> @NotNull(message = "{xxx.xxx.xx}", groups = {AddGroup.class, UpdateGroup.class})
    // 详情请参考https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/

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
    private BigDecimal quota;

    @ApiModelProperty(value = "升级条件  1.累计积分 2.消费额度 3.同时满足")
    private Integer upgrade;

    @ApiModelProperty(value = "等级排序")
    private Integer sort;

    @ApiModelProperty(value = "1.或 2. 且")
    private Integer relation;

}