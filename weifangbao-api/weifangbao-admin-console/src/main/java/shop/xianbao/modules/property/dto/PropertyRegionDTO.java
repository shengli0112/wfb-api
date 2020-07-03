package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Data
@ApiModel(value = "楼盘区域表")
public class PropertyRegionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @Range(min = 0, max = 1, message = "isShow值非法", groups = UpdateGroup.class)
    @ApiModelProperty(value = "是否显示 0否 1是")
    private Integer isShow;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "更新者id")
    private Long updater;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "是否新楼盘")
    private Integer isNew;

    @ApiModelProperty(value = "是否折扣楼盘")
    private Integer isDiscount;

    @ApiModelProperty(value = "是否网红楼盘")
    private Integer isPop;

}