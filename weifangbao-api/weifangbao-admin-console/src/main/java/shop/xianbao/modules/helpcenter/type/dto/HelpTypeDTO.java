package shop.xianbao.modules.helpcenter.type.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 帮助类型DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
@ApiModel(value = "帮助类型")
public class HelpTypeDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别名称")
    @NotBlank(message = "{common.name.require}")
    private String name;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "{common.sort.require}")
    @Min(value = 0, message = "{common.sort.min}")
    private Integer sort;
}