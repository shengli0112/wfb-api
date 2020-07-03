package shop.xianbao.modules.news.articleclass.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增文章分类DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "添加文章分类")
public class ArticleClassDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级分类")
    @NotNull(message = "{articleclass.pid.require}", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0, message = "{articleclass.pid.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Long pid;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "{common.name.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
}