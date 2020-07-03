package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 楼盘区域表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-20
 */
@Data
@ApiModel(value = "楼盘区域表-列表")
public class PropertyRegionListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;
}