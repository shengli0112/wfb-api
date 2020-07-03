package shop.xianbao.modules.pay.service;

import me.chanjar.weixin.common.error.WxErrorException;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.pay.DTO.PayParamDTO;

/**
 * @program: AliPayService
 * @description:
 * @author: yh
 * @create: 2019-08-16 16:38
 **/
public interface PayService {
    /**
     * 普通订单支付
     *
     * @param payParamDTO
     * @return
     */
    Object orderPay(PayParamDTO payParamDTO);

    /**
     * 拼团订单支付
     *
     * @param payParamDTO
     * @return
     */
    Object groupPay(PayParamDTO payParamDTO);

    /**
     * 获取微信公众号配置
     *
     * @return
     */
    Result getWxConfig(String returnUrl) throws WxErrorException;
}
