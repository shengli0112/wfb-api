package shop.xianbao.modules.elink.dto;

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
 * 易联云打印机
 * 新增/详情/修改接口的DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@ApiModel(value = "易联云打印机")
public class ElinkPrinterDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long storeId;

    @ApiModelProperty(value = "打印机名称（自定义）")
    private String printName;

    @ApiModelProperty(value = "易联云打印机终端号")
    private String machineCode;

    @ApiModelProperty(value = "易联云终端密钥")
    private String msign;

    @ApiModelProperty(value = "是否默认 0否 1是")
    private Integer isDefault;

    @ApiModelProperty(value = "排序字段 小值在前")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}