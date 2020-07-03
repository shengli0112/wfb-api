package shop.xianbao.modules.advertising.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 广告表
 * 列表DTO
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@ApiModel(value = "广告表-列表")
public class AdvListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "广告自增标识编号")
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