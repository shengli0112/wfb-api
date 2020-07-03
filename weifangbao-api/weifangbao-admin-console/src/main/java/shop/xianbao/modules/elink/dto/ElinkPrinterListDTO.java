package shop.xianbao.modules.elink.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 易联云打印机
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@ApiModel(value = "易联云打印机-列表")
public class ElinkPrinterListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "id")
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
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

}