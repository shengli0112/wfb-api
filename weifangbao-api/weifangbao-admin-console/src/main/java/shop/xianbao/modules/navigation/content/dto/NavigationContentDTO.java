package shop.xianbao.modules.navigation.content.dto;

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
 * 导航管理-导航内容
 * 新增/详情/修改接口的DTO
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
@ApiModel(value = "导航管理-导航内容")
public class NavigationContentDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "导航名称")
    @NotBlank(message = "{common.name.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "导航类型 0-PC端,1-手机端")
    @NotNull(message = "{common.type.require}", groups = {AddGroup.class, UpdateGroup.class})
    @Range(min = 0, max = 1, message = "{common.type.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer type;

    @ApiModelProperty(value = "链接地址来源类型: 1-下拉框选择,2-输入框")
    @NotNull(message = "{navigation_content.urlSourceType.require}", groups = {AddGroup.class, UpdateGroup.class})
    @Range(min = 1, max = 2, message = "{navigation_content.urlSourceType.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer urlSourceType;

    @ApiModelProperty(value = "所属导航模块ID")
    private Long moduleId;

    @ApiModelProperty(value = "自定义链接")
    private String customUrl;

    @ApiModelProperty(value = "是否新窗口打开 0-否,1-是")
    @Range(min = 0, max = 1, message = "{navigation_content.openInNewWindowFlag.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer openInNewWindowFlag;

    @ApiModelProperty(value = "是否显示 0-否,1-是")
    @Range(min = 0, max = 1, message = "{common.showFlag.range}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer showFlag;

    @ApiModelProperty(value = "导航图标URL")
    private String logoUrl;

}