package shop.xianbao.modules.pay.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: PayParamDTO
 * @description: 支付参数DTO
 * @author: yh
 * @create: 2019-07-11 13:29
 **/
@Data
public class RefundParamDTO {
    private Long payTypeId;

    private Long afterSaleId;

    private String outTradeNo;

    private BigDecimal refundAmount;

    private String refundReason;

    private String outRequestNo;

}
