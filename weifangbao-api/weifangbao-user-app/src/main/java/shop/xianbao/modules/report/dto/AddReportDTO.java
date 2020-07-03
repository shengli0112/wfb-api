package shop.xianbao.modules.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@Data
@ApiModel(value = "交易表")
public class AddReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "", groups = {AddGroup.class})
    private Long propertyId;

    @NotNull(message = "", groups = {AddGroup.class})
    private List<ReportCustomer> reportCustomers;

    @Data
    public static class ReportCustomer {

        private Long customerId;

        @NotBlank(message = "", groups = {AddGroup.class})
        @ApiModelProperty(value = "客户姓名")
        private String customerName;

        @NotNull(message = "", groups = {AddGroup.class})
        @ApiModelProperty(value = "客户性别 0保密 1男 2女")
        private Integer customerGender;

        @NotNull(message = "", groups = {AddGroup.class})
        @ApiModelProperty(value = "客户手机号")
        private String mobile;

        @NotNull(message = "", groups = {AddGroup.class})
        @Range(min = 0, max = 1, message = "", groups = {AddGroup.class})
        @ApiModelProperty(value = "是否保密 0否 1是")
        private Integer isSecret;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @NotNull(message = "", groups = {AddGroup.class})
        private Date appointTime;
    }
}