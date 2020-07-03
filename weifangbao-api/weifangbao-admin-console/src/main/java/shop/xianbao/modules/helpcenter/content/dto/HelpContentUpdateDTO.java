package shop.xianbao.modules.helpcenter.content.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * 更新帮助内容DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
@ApiModel(value = "更新帮助内容")
public class HelpContentUpdateDTO extends HelpContentSaveDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帮助内容ID")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;
}
