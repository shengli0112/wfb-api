package shop.xianbao.modules.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.modules.pay.config.ali.AliPayUtil;
import shop.xianbao.modules.pay.service.AliPayService;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @program: AliPayServiceImpl
 * @description:
 * @author: yh
 * @create: 2019-08-07 18:10
 **/
@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private PayTypeService payTypeService;

    private AlipayClient alipayClient;

    @Override
    public JSONObject transferAccounts(Long id, String account, BigDecimal amount) {
        getAliPayClient();
        JSONObject result = new JSONObject();
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" + "\"out_biz_no\":\"" + id + "\"," + "\"payee_type\":\"ALIPAY_USERID\"," + "\"payee_account\":\"" + account + "\"," + "\"amount\":\"" + amount + "\"" + "  }");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            result.put("success", false);
            result.put("msg", e.getErrMsg());
            return result;
        }
        if (response.isSuccess()) {
            result.put("success", true);
            result.put("msg", null);
            //返回成功
        }
        return result;
    }

    private void getAliPayClient() {
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("ali_pay", "ALL");
        if (Objects.equals(null, payTypeEntity)) {
            throw new XianbaoException("支付渠道未配置");
        }
        alipayClient = AliPayUtil.getAliPayClient(payTypeEntity.getConfig());
    }

    @Override
    public JSONObject queryTransfer(Long id) {
        getAliPayClient();
        JSONObject result = new JSONObject();
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizContent("{" + "\"out_biz_no\":\"" + id + "\"" + "  }");
        AlipayFundTransOrderQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            result.put("success", false);
            result.put("msg", e.getErrMsg());
            return result;
        }
        if (response.isSuccess()) {
            result.put("success", true);
            result.put("msg", null);
            //返回成功
        } else {
            result.put("success", false);
            result.put("msg", response.getFailReason());
            //返回失败
        }
        return result;
    }

    @Override
    public JSONObject queryRefund(Long payTypeId, String outTradeNo, String outRequestNo) {
        JSONObject result = new JSONObject();
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeById(payTypeId);
        alipayClient = AliPayUtil.getAliPayClient(payTypeEntity.getConfig());
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setOutRequestNo(outRequestNo);
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            result.put("success", false);
            result.put("msg", e.getErrMsg());
        }
        if (response.isSuccess()) {
            result.put("success", true);
            result.put("msg", null);
        } else {
            result.put("success", false);
            result.put("msg", response.getMsg());
        }
        return result;
    }
}
