package shop.xianbao.modules.pay.config.ali;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import shop.xianbao.modules.pay.constant.AlipayUrlConstants;

import java.util.Objects;

/**
 * @program: AliPayUtil
 * @description:
 * @author: yh
 * @create: 2019-08-07 17:24
 **/
public class AliPayUtil {
    private static final String ERROR_MSG_APPID_NULL = "alipay application's appid cann't be null";

    private static final String ERROR_MSG_PRIVATE_KEY_NULL = "alipay application's privateKey cann't be null";

    private static final String ERROR_MSG_ALIPAY_PUBLIC_KEY_NULL = "alipay's publicKey cann't be null";

    /**
     * 获取支付宝支付配置
     *
     * @param configParam
     * @return
     */
    public static AlipayClient getAliPayClient(String configParam) {

        JSONObject properties = JSONObject.parseObject(configParam);

        String gatewayUrl = AlipayUrlConstants.gateway(properties.getBoolean("dev"));
        String appId = Objects.requireNonNull(properties.getString("appId"), ERROR_MSG_APPID_NULL);
        String privateKey = Objects.requireNonNull(properties.getString("privateKey"), ERROR_MSG_PRIVATE_KEY_NULL);
        String format = AlipayConstants.FORMAT_JSON;
        String charset = AlipayConstants.CHARSET_UTF8;
        String aliPayPublicKey = Objects.requireNonNull(properties.getString("aliPayPublicKey"), ERROR_MSG_ALIPAY_PUBLIC_KEY_NULL);
        String signType = AlipayConstants.SIGN_TYPE_RSA.equals(properties.getString("signType")) ? AlipayConstants.SIGN_TYPE_RSA : AlipayConstants.SIGN_TYPE_RSA2;
        return new DefaultAlipayClient(gatewayUrl, appId, privateKey, format, charset, aliPayPublicKey, signType);
    }
}
