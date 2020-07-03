package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
@ApiModel(value = "帮找房表")
public class HelpFindHouseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private Long unionId;

    @NotNull(groups = AddGroup.class)
    @ApiModelProperty(value = "购房区域")
    private Long areaId;

    @ApiModelProperty(value = "所在区域名称")
    private String areaName;

    @NotNull(groups = AddGroup.class)
    @ApiModelProperty(value = "购房预算")
    private BigDecimal budget;

    @ApiModelProperty(value = "户型")
    private String apartment;

    @ApiModelProperty(value = "备注")
    private String remark;

    @NotNull(groups = UpdateGroup.class)
    @Range(min = 0, max = 1, groups = {UpdateGroup.class}, message = "status值非法")
    @ApiModelProperty(value = "状态 0 停用 1启用")
    private Integer status;
}