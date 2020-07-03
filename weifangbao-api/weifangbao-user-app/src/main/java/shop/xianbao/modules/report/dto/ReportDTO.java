package shop.xianbao.modules.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.modules.report.enums.ReportStatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@Data
@ApiModel(value = "交易表")
public class ReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private Long unionId;

    @ApiModelProperty(value = "报备人昵称")
    private String nickname;

    @ApiModelProperty(value = "报备人手机号")
    private String reportMobile;

    @ApiModelProperty(value = "楼盘id")
    private Long propertyId;

    @ApiModelProperty(value = "楼盘名称")
    private String propertyName;

    @ApiModelProperty(value = "佣金")
    private BigDecimal commission;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "客户性别")
    private Integer customerGender;

    @ApiModelProperty(value = "客户手机号")
    private String mobile;

    @ApiModelProperty(value = "是否保密")
    private Integer isSecret;

    @ApiModelProperty(value = "看房时间")
    private Date appointTime;

    @ApiModelProperty(value = "认购时间")
    private Date subscriptionTime;

    @ApiModelProperty(value = "签约时间")
    private Date signingTime;

    @ApiModelProperty(value = "失效时间")
    private Date invalidTime;

    @ApiModelProperty(value = "报备状态 0 预留 1已报备 2已看房 3已认购 4已签约 5已失效")
    private Integer status;

    @ApiModelProperty(value = "报备状态 0 预留 1已报备 2已看房 3已认购 4已签约 5已失效")
    private String statusName;

    public String getStatusName() {
        return EnumUtil.getEnumBycode(ReportStatusEnum.class, status).getName();
    }

}