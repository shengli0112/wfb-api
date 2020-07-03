package shop.xianbao.modules.pay.strategy;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.stereotype.Component;
import shop.xianbao.modules.pay.config.ali.AliPayUtil;
import shop.xianbao.modules.pay.dto.RefundParamDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

/**
 * @program: AliPayStrategy
 * @description: 支付宝支付实现
 * @author: yh
 * @create: 2019-06-06 15:56
 **/
@Component
public class AliPayStrategy implements PayStrategy {

    private AlipayClient alipayClient;

    @Override
    public <T> T refund(PayTypeEntity payTypeEntity, RefundParamDTO refundParamDTO) {
        alipayClient = AliPayUtil.getAliPayClient(payTypeEntity.getConfig());
        JSONObject result = new JSONObject();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(refundParamDTO.getOutTradeNo());
        model.setRefundAmount(refundParamDTO.getRefundAmount().toString());
        model.setRefundReason(refundParamDTO.getRefundReason());
        model.setOutRequestNo(refundParamDTO.getOutRequestNo());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.put("success", true);
                result.put("msg", null);
            } else {
                result.put("success", false);
                result.put("msg", response.getMsg());
            }
        } catch (AlipayApiException e) {
            result.put("success", false);
            result.put("msg", e.getErrMsg());
        }

        return (T)result;
    }

}
