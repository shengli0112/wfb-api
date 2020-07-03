package shop.xianbao.modules.pay.strategy;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.common.utils.IpUtils;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.pay.DTO.PayParamDTO;
import shop.xianbao.modules.pay.config.wx.WxPayProperties;
import shop.xianbao.modules.pay.config.wx.WxPayUtil;
import shop.xianbao.modules.pay.constant.PayConstant;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

import java.math.BigDecimal;

/**
 * @program: WeChatPayStrategy
 * @description: 微信支付实现类
 * @author: yh
 * @create: 2019-06-05 13:48
 **/
@Component
public class WeChatPayStrategy implements PayStrategy {

    private WxMpService wxMpService;

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UnionUserService unionUserService;

    @Override
    public JSONObject pay(PayTypeEntity payTypeEntity, PayParamDTO payParamDTO) {
        String tradeType = payParamDTO.getTradeType();
        if (tradeType.contains("_")) {
            tradeType = tradeType.split("_")[0];
        }
        Integer passBackParams = payParamDTO.getPassBackParams();
        String notifyPath = "";
        switch (passBackParams) {
            case 0:
                //普通订单回调路径
                notifyPath = wxPayProperties.getOrderNotifyPath();
                break;
            case 2:
                //拼团订单回调路径
                notifyPath = wxPayProperties.getGroupNotifyPath();
                break;
            default:
                break;
        }
        WxPayService wxPayService = WxPayUtil.getWxPayService(payTypeEntity.getConfig(), tradeType, notifyPath);
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setVersion("1.0");
        if (PayConstant.WxConstant.TRADE_TYPE_JSPAI.equals(tradeType)) {
            String openId = payParamDTO.getOpenId();
            //处理openId
            //            String openId = unionUserService.getOpenId(payParamDTO.getUnionId());
            //            String openId = "otMZTwoIpEzEj0cZQOGRrEf02GB8";
            if (StringUtils.isBlank(openId)) {
                //微信获取授权
                throw new XianbaoException("openId不能为空");
            } else {
                request.setOpenid(openId);
            }
        }
        request.setBody("订单支付");
        request.setFeeType("CNY");
        request.setOutTradeNo(payParamDTO.getOrderSn());
        request.setTotalFee(payParamDTO.getOrderAmount().multiply(new BigDecimal(100)).intValue());
        request.setTradeType(tradeType);
        request.setSpbillCreateIp(IpUtils.getLocalFirstIpv4());
        try {
            Object wxPayResult = wxPayService.createOrder(request);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("payResult", wxPayResult);
            jsonObject.put("payCode", payTypeEntity.getId());
            return jsonObject;
        } catch (WxPayException e) {
            //TODO 注释print
            e.printStackTrace();
            if (!PayConstant.RETURN_VALUE_SUCCESS.equals(e.getReturnCode())) {
                throw new XianbaoException(e.getReturnMsg());
            } else {
                throw new XianbaoException(e.getErrCodeDes());
            }
        }
    }
}
