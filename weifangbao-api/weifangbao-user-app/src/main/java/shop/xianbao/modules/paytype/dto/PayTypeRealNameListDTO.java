package shop.xianbao.modules.paytype.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

/**
 * 支付方式
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
@Data
@ApiModel(value = "支付方式-列表")
public class PayTypeRealNameListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "支付方式code")
    private String payCode;

    @ApiModelProperty(value = "支付方式名称")
    private String payName;

    @ApiModelProperty(value = "是否需要实名认证 0否 1是")
    private Integer isRealName;

    @ApiModelProperty(value = "实名认证状态 0申请中 1认证中  2认证通过 3认证失败")
    private Integer status;

}