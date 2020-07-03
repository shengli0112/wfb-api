package shop.xianbao.modules.pay.strategy;

import com.alibaba.fastjson.JSONObject;
import shop.xianbao.modules.pay.DTO.PayParamDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

/**
 * @program: PayStrategy
 * @description: 通用支付方法骨架
 * @author: yh
 * @create: 2019-06-05 09:50
 **/
public interface PayStrategy {

    /**
     * 支付
     *
     * @param payTypeEntity
     * @param payParamDTO
     * @return
     */
    JSONObject pay(PayTypeEntity payTypeEntity, PayParamDTO payParamDTO);

}
