package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 楼盘收藏表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-20
 */
@Data
@ApiModel(value = "楼盘收藏表-列表")
public class PropertyCollectListDTO extends PropertyListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private Long unionId;

    @ApiModelProperty(value = "楼盘id")
    private Long propertyId;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

}