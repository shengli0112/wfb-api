package shop.xianbao.modules.helpcenter.content.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增帮助内容DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
@ApiModel(value = "新增帮助内容")
public class HelpContentSaveDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "{common.title.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String title;

    @ApiModelProperty(value = "帮助类别ID")
    @NotNull(message = "{helpcontent.typeId.require}", groups = {AddGroup.class, UpdateGroup.class})
    private Long typeId;

    @ApiModelProperty(value = "正文")
    @NotBlank(message = "{helpcontent.content.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String content;

    @ApiModelProperty(value = "是否显示,0-否,1-是")
    @Range(min = 0, max = 1, message = "{common.showFlag.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer showFlag;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
}