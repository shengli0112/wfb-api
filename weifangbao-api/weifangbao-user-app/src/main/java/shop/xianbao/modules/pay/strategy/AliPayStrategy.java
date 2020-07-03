package shop.xianbao.modules.pay.strategy;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.utils.HttpUtil;
import shop.xianbao.modules.pay.DTO.PayParamDTO;
import shop.xianbao.modules.pay.config.ali.AliPayUtil;
import shop.xianbao.modules.pay.constant.PayConstant;
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
    public JSONObject pay(PayTypeEntity payTypeEntity, PayParamDTO payParamDTO) {
        alipayClient = AliPayUtil.getAliPayClient(payTypeEntity.getConfig());

        switch (payParamDTO.getTradeType()) {
            case PayConstant.AliPayConstant.TRADE_TYPE_APP:
                try {
                    AlipayTradeCreateModel model = new AlipayTradeCreateModel();
                    model.setSubject(payParamDTO.getOrderSn());
                    model.setOutTradeNo(payParamDTO.getOrderSn());
                    model.setTotalAmount(payParamDTO.getOrderAmount().toString());
                    AlipayTradeCreateRequest request = getAlipayTradeCreateRequest(payTypeEntity, payParamDTO, model);
                    AlipayTradeCreateResponse response = alipayClient.execute(request);
                    String body = response.getBody();
                    JSONObject jsonBody = JSONObject.parseObject(body);
                    String passBackParams = "{'promType':'" + payParamDTO.getPassBackParams() + "'}";
                    jsonBody.put("passback_params", HttpUtil.urlEncode(passBackParams, "UTF-8"));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("payResult", jsonBody);
                    jsonObject.put("payCode", payTypeEntity.getId());
                    return jsonObject;
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                    throw new XianbaoException(e.getErrMsg());
                }
            case PayConstant.AliPayConstant.TRADE_TYPE_MWEB:
                try {
                    AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
                    model.setSubject(payParamDTO.getOrderSn());
                    model.setOutTradeNo(payParamDTO.getOrderSn());
                    model.setTotalAmount(payParamDTO.getOrderAmount().toString());
                    String passBackParams = "{'promType':'" + payParamDTO.getPassBackParams() + "'}";
                    model.setPassbackParams(HttpUtil.urlEncode(passBackParams, "UTF-8"));
                    AlipayTradeWapPayRequest request = getAlipayTradeWapPayRequest(payTypeEntity, payParamDTO, model);
                    String form = alipayClient.pageExecute(request).getBody();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("payResult", form);
                    jsonObject.put("payCode", payTypeEntity.getId());
                    return jsonObject;
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                    throw new XianbaoException(e.getErrMsg());
                }
            default:
                break;
        }
        return null;
    }

    private AlipayTradeCreateRequest getAlipayTradeCreateRequest(PayTypeEntity payTypeEntity, PayParamDTO payParamDTO, AlipayTradeCreateModel model) {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setBizModel(model);
        JSONObject properties = JSONObject.parseObject(payTypeEntity.getConfig());
        //异步通知地址
        request.setNotifyUrl(properties.getString("notifyUrl"));
        request.setReturnUrl(payParamDTO.getReturnUrl());
        return request;
    }

    @NotNull
    private AlipayTradeWapPayRequest getAlipayTradeWapPayRequest(PayTypeEntity payTypeEntity, PayParamDTO payParamDTO, AlipayTradeWapPayModel model) {
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setBizModel(model);
        JSONObject properties = JSONObject.parseObject(payTypeEntity.getConfig());
        //异步通知地址
        request.setNotifyUrl(properties.getString("notifyUrl"));
        request.setReturnUrl(payParamDTO.getReturnUrl());
        return request;
    }

}
