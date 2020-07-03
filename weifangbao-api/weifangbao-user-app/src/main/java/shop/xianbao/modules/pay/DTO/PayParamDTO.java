package shop.xianbao.modules.pay.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: PayParamDTO
 * @description: 支付参数DTO
 * @author: yh
 * @create: 2019-07-11 13:29
 **/
@Data
public class PayParamDTO {
    private String payCode;

    private String tradeType;

    private Long orderId;

    private String returnUrl;

    private String orderSn;

    private BigDecimal orderAmount;

    private Long unionId;

    private String openId;

    Integer passBackParams;

}
