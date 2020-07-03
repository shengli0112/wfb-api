package shop.xianbao.modules.advertising.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 广告位表
 * 列表DTO
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@ApiModel(value = "广告位表-列表")
public class AdvPositionListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "广告位置id")
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
    private Integer sort;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}