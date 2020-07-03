package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮找房表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-15
 */
@Data
@ApiModel(value = "帮找房表-列表")
public class HelpFindHouseListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private Long unionId;

    private String nickname;

    private String mobile;

    @ApiModelProperty(value = "购房区域")
    private Long areaId;

    private String areaName;

    @ApiModelProperty(value = "购房预算")
    private BigDecimal budget;

    @ApiModelProperty(value = "户型")
    private String apartment;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态 0 停用 1启用")
    private Integer status;

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

}