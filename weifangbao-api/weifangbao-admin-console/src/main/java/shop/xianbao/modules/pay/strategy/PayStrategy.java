package shop.xianbao.modules.pay.strategy;

import shop.xianbao.modules.pay.dto.RefundParamDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

/**
 * @program: PayStrategy
 * @description: 通用支付方法骨架
 * @author: yh
 * @create: 2019-06-05 09:50
 **/
public interface PayStrategy {

    /**
     * 退款接口
     *
     * @param payTypeEntity
     * @param refundParamDTO
     * @param <T>
     * @return
     */
    <T> T refund(PayTypeEntity payTypeEntity, RefundParamDTO refundParamDTO);

}
