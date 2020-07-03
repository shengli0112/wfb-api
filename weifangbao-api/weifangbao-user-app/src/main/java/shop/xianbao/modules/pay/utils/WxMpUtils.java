package shop.xianbao.modules.pay.utils;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * @program: WeChatMpConfig
 * @description:
 * @author: yh
 * @create: 2019-05-31 15:14
 **/
public class WxMpUtils {

    @Bean
    public static WxMpService getWxMpService(String config) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage(config));
        return wxMpService;
    }

    @Bean
    public static WxMpConfigStorage wxMpConfigStorage(String config) {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        JSONObject paramObj = JSONObject.parseObject(config);
        wxMpConfigStorage.setAppId(paramObj.getString("appId"));
        wxMpConfigStorage.setSecret(paramObj.getString("appSecret"));
        return wxMpConfigStorage;
    }

}
