package shop.xianbao.modules.pay.config.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;

/**
 * @program: WeChatMpConfig
 * @description:
 * @author: yh
 * @create: 2019-05-31 15:14
 **/
public class WxMaUtils {

    @Bean
    public static WxMaService getWxMaService(String config) {
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfigStorage(config));
        return wxMaService;
    }

    @Bean
    public static WxMaConfig wxMaConfigStorage(String config) {
        WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
        JSONObject paramObj = JSONObject.parseObject(config);
        wxMaConfig.setAppid(paramObj.getString("appId"));
        wxMaConfig.setSecret(paramObj.getString("appSecret"));
        return wxMaConfig;
    }

    //    public static WxMpService getWxMpService(String config) {
    //        WxMpService wxMpService = new WxMpServiceImpl();
    //        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
    //        JSONObject paramObj = JSONObject.parseObject(config);
    //        configStorage.setAppId(paramObj.getString("appId"));
    //        configStorage.setSecret(paramObj.getString("appSecret"));
    //        wxMpService.setWxMpConfigStorage(configStorage);
    //        return wxMpService;
    //    }
}
