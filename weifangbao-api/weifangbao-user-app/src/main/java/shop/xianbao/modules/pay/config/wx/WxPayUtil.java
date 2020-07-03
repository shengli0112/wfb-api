package shop.xianbao.modules.pay.config.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yanghuan
 */
public class WxPayUtil {

    /**
     * 获取微信支付配置
     *
     * @param configParam
     * @param tradeType
     * @return
     */
    public static WxPayService getWxPayService(
        String configParam, String tradeType, String notifyUrl) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        JSONObject paramObj = JSON.parseObject(configParam);
        wxPayConfig.setAppId(paramObj.getString("appId"));
        wxPayConfig.setMchId(paramObj.getString("mchId"));
        wxPayConfig.setMchKey(paramObj.getString("mchKey"));
        String keyPath = paramObj.getString("keyPath");
        if (StringUtils.isNotBlank(keyPath)) {
            wxPayConfig.setKeyPath(keyPath);
        }
        wxPayConfig.setNotifyUrl(paramObj.getString("notifyUrl") + notifyUrl);
        wxPayConfig.setTradeType(tradeType);
        wxPayConfig.setSignType("MD5");
        // 可以指定是否使用沙箱环境
        wxPayConfig.setUseSandboxEnv(paramObj.getBoolean("dev"));

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;

    }
}
