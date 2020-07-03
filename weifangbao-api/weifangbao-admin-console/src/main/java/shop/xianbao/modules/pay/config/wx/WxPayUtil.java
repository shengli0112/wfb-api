package shop.xianbao.modules.pay.config.wx;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

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
        String configParam, String tradeType, WxPayProperties wxPayProperties) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        JSONObject paramObj = JSONObject.parseObject(configParam);
        wxPayConfig.setAppId(paramObj.getString("appId"));
        wxPayConfig.setMchId(wxPayProperties.getMchId());
        wxPayConfig.setMchKey(wxPayProperties.getMchKey());
        if (StringUtils.isNotBlank(wxPayProperties.getKeyPath()) && StringUtils.isNotBlank(paramObj.getString("certLocalPath"))) {
            wxPayConfig.setKeyPath(wxPayProperties.getKeyPath() + File.separator + paramObj.getString("certLocalPath"));
        }
        wxPayConfig.setNotifyUrl(wxPayProperties.getNotifyUrl());
        wxPayConfig.setTradeType(tradeType);
        wxPayConfig.setSignType("MD5");
        // 可以指定是否使用沙箱环境
        wxPayConfig.setUseSandboxEnv(wxPayProperties.getDev());

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;

    }
}
