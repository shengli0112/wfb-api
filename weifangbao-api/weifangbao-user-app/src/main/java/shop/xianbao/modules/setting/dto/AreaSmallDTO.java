package shop.xianbao.modules.setting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * 地区表/行政划分表
 * 新增/详情/修改接口的DTO
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@Data
@ApiModel(value = "地区表/行政划分表")
public class AreaSmallDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "{common.name.require}", groups = {AddGroup.class})
    private String name;

    @ApiModelProperty(value = "归属ID")
    @NotNull(message = "{area.pid.require}", groups = {AddGroup.class})
    //父级id
    private Long pid;

    //由父级决定
    @ApiModelProperty(value = "等级类型，1-省；2-市; 3-县、区;  4:镇; 5:乡村")
    private Integer level;

    @ApiModelProperty(value = "排序字段 小值在前")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "子节点列表")
    private List<AreaSmallDTO> child_list;

}