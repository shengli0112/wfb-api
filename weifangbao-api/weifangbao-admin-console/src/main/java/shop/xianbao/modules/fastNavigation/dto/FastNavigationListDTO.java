package shop.xianbao.modules.fastNavigation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 导航管理-导航内容
 * 列表DTO
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
@Data
@ApiModel(value = "导航管理-导航内容-列表")
public class FastNavigationListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "导航名称")
    private String name;

    @ApiModelProperty(value = "导航图标URL")
    private String icon;

    @ApiModelProperty(value = "终端类型 0-PC端,1-手机端")
    private Integer type;

    @ApiModelProperty(value = "页面链接")
    private String pageUrl;

    @ApiModelProperty(value = "所属导航模块ID")
    private Long positionId;

    @ApiModelProperty(value = "布局类型 1: 1*4  2:3*4  3:2*5  4:3*5")
    private Integer layoutType;

    @ApiModelProperty(value = "是否显示 0-否,1-是")
    private Integer showFlag;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建者ID")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者ID")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

}