package shop.xianbao.modules.setting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

/**
 * 地区表/行政划分表
 * 列表DTO
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@Data
@ApiModel(value = "地区表/行政划分表-列表")
public class AreaListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "归属ID")
    private long pid;

    @ApiModelProperty(value = "等级类型，1-省；2-市; 3-县、区;  4:镇; 5:乡村")
    private Integer level;

    @ApiModelProperty(value = "区号")
    private String cityCode;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "城市拼音")
    private String pinyin;

    @ApiModelProperty(value = "详细名称")
    private String mergerName;

    @ApiModelProperty(value = "简短名称")
    private String shortName;

    @ApiModelProperty(value = "经度（百度地图）")
    private String lng;

    @ApiModelProperty(value = "纬度（百度地图）")
    private String lat;

    @ApiModelProperty(value = "状态 暂未使用")
    private Integer status;

    @ApiModelProperty(value = "是否为热门城市 0=不是 1=是 暂未使用")
    private Integer isHot;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

}