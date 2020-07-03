package shop.xianbao.modules.pay.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * @program: AliPayService
 * @description:
 * @author: yh
 * @create: 2019-08-07 18:10
 **/
public interface AliPayService {

    /**
     * 支付宝转账接口
     *
     * @param id      系统id
     * @param account 收款账户
     * @param amount  金额
     * @return
     */
    JSONObject transferAccounts(Long id, String account, BigDecimal amount);

    /**
     * 查询转账订单接口
     *
     * @param id
     * @return
     */
    JSONObject queryTransfer(Long id);

    /**
     * 查询订单退款接口
     *
     * @param outTradeNo
     * @param outRequestNo
     * @return
     */
    JSONObject queryRefund(Long payTypeId, String outTradeNo, String outRequestNo);
}
