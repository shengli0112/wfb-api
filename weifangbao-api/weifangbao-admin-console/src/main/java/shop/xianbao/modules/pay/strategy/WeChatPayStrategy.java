package shop.xianbao.modules.pay.strategy;

import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.modules.pay.config.wx.WxPayProperties;
import shop.xianbao.modules.pay.dto.RefundParamDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

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

    //    @Autowired
    //    private OrderService orderService;

    @Override
    public <T> T refund(PayTypeEntity payTypeEntity, RefundParamDTO refundParamDTO) {
        return null;
    }
}
