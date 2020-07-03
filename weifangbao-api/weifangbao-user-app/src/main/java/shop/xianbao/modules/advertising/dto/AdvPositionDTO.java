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
import java.math.BigDecimal;

/**
 * 广告位表
 * 新增/详情/修改接口的DTO
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@ApiModel(value = "广告位表")
public class AdvPositionDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "广告位置名")
    private String name;

    @ApiModelProperty(value = "广告位简介")
    private String intro;

    @ApiModelProperty(value = "广告展示方式：0幻灯片多广告展示,1单广告展示")
    private Integer displayMode;

    @ApiModelProperty(value = "广告位高度")
    private Integer height;

    @ApiModelProperty(value = "广告位宽度")
    private Integer width;

    @ApiModelProperty(value = "广告位单价")
    private BigDecimal price;

    @ApiModelProperty(value = "广告位点击率")
    private Integer clickNum;

    @ApiModelProperty(value = "默认占位图")
    private String defaultSpaceImage;

    @ApiModelProperty(value = "")
    private Integer displayTerminal;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}