package shop.xianbao.modules.advertising.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 广告表
 * 新增/详情/修改接口的DTO
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@ApiModel(value = "广告表")
public class AdvDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "广告位id")
    private Long apId;

    @ApiModelProperty(value = "广告内容描述")
    private String title;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "广告内容图片URL")
    private String imageUrl;

    @ApiModelProperty(value = "广告点击率")
    private Integer clickNum;

    @ApiModelProperty(value = "排序字段")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

    /**
     * 广告位置name
     */
    private String apName;
}