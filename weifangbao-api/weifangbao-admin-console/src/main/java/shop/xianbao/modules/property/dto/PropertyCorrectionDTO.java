package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.property.enums.CorrectionStatusEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
@ApiModel(value = "楼盘纠错表")
public class PropertyCorrectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "楼盘id")
    private Long propertyId;

    @ApiModelProperty(value = "楼盘名称")
    private String propertyName;

    @ApiModelProperty(value = "错误描述")
    private String description;

    @ApiModelProperty(value = "联系方式")
    private String contactInfo;

    @ApiModelProperty(value = "截图")
    private String screenshot;

    @ApiModelProperty(value = "上报者id")
    private Long reporterId;

    private String nickname;

    /**
     * 处理状态 0未处理 1已处理
     */
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    @Range(min = 0, max = 1, message = "status值非法", groups = UpdateGroup.class)
    private Integer status;

    private String statusName;

    public String getStatusName() {
        return EnumUtil.getEnumBycode(CorrectionStatusEnum.class, status).getName();
    }

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