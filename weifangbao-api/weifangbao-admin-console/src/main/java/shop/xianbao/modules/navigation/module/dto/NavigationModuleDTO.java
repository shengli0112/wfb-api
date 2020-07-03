package shop.xianbao.modules.navigation.module.dto;

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
import javax.validation.constraints.Null;

/**
 * 导航管理-模块管理
 * 新增/详情/修改接口的DTO
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
@ApiModel(value = "导航管理-模块管理")
public class NavigationModuleDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "模块名称")
    @NotBlank(message = "{common.name.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "链接地址")
    @NotBlank(message = "{common.url.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String url;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "导航模块类型 0-PC端,1-手机端")
    @NotNull(message = "{common.type.require}", groups = {AddGroup.class, UpdateGroup.class})
    @Range(min = 0, max = 1, message = "{common.type.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer type;

}