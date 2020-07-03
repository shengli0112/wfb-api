package shop.xianbao.common.elink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.elink.api.ELinkAPI;
import shop.xianbao.common.elink.config.ELinkConfig;
import shop.xianbao.common.elink.service.ElinkService;

/**
 * 易联云接口工具类
 */
@Service
public class ElinkServiceImpl implements ElinkService {

    @Autowired
    public ELinkConfig eLinkConfig;

    /**
     * token
     */
    //    public String token = "6d02487e52604cd1bcb23cbc4e08dfe2";
    //
    //    /**
    //     * 刷新token需要的 refreshtoken
    //     */
    //    public String refresh_token = "f4f58624496c4b25a2169869c2c15db5";

    private static class SingleMethods {
        private static final ElinkServiceImpl COCOS_MANGER = new ElinkServiceImpl();
    }

    public static final ElinkServiceImpl getInstance() {
        return SingleMethods.COCOS_MANGER;
    }

    //    /**
    //     * 开放式初始化
    //     *
    //     * @param client_id
    //     * @param client_secret
    //     * @param code
    //     */
    //    public void init(String client_id, String client_secret, String code) {
    //        eLinkConfig.getClientId() = client_id;
    //        CLIENT_SECRET = client_secret;
    //        CODE = code;
    //    }
    //
    //    /**
    //     * 自有初始化
    //     *
    //     * @param client_id
    //     * @param client_secret
    //     */
    //    public void init(String client_id, String client_secret, String grant_type, String scope) {
    //        eLinkConfig.getClientId() = client_id;
    //        CLIENT_SECRET = client_secret;
    //        GRANT_TYPE = grant_type;
    //        "all" = scope;
    //    }

    /**
     * 开放应用
     */
    public String getToken(String CODE) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String result = ELinkAPI.getToken(eLinkConfig.getClientId(),
            "client_credentials",
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            CODE,
            "all",
            timestamp,
            ELinkAPI.getuuid());
        //        try {
        //            JSONObject json = new JSONObject(result);
        //            JSONObject body = json.getJSONObject("body");
        //            token = body.getString("access_token");
        //            refresh_token = body.getString("refresh_token");
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            System.out.println("getToken出现Json异常！" + e);
        //        }
        return result;
    }

    /**
     * 自有应用服务
     */
    @Override
    public String getFreedomToken() {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String result = ELinkAPI
            .getToken(eLinkConfig.getClientId(), "client_credentials", ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), "all", timestamp, ELinkAPI.getuuid());
        //        try {
        //            JSONObject json = new JSONObject(result);
        //            JSONObject body = json.getJSONObject("body");
        //            token = body.getString("access_token");
        //            refresh_token = body.getString("refresh_token");
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            System.out.println("getFreedomToken出现Json异常！" + e);
        //        }
        return result;
    }

    /**
     * 刷新token
     */
    @Override
    public String refreshToken(String refresh_token) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String result = ELinkAPI.refreshToken(eLinkConfig.getClientId(),
            "refresh_token",
            "all",
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            refresh_token,
            ELinkAPI.getuuid(),
            timestamp);
        //        try {
        //            JSONObject json = new JSONObject(result);
        //            JSONObject body = json.getJSONObject("body");
        //            token = body.getString("access_token");
        //            refresh_token = body.getString("refresh_token");
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            System.out.println("refreshToken出现Json异常！" + e);
        //        }
        return result;
    }

    /**
     * 添加终端授权 开放应用服务模式不需要此接口 ,自有应用服务模式所需参数
     * result
     * {"error":"0","error_description":"success"}
     * {"error":"2","error_description":"client_id不存在"}
     * {"error":"11","error_description":"sign验证失败"}
     * {"error":"12","error_description":"缺少必要参数"}
     * {"error":"16","error_description":"不支持k1,k2,k3机型"}
     * {'error':'18', 'error_description':'access_token过期或错误,请刷新access_token或者重新授权'},
     * {'error':'33', 'error_description':'Uuid不合法'}
     * {"error":"34","error_description":"非法参数"}
     */
    @Override
    public String addPrinter(String token, String machine_code, String msign, String printName) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.addPrinter(eLinkConfig.getClientId(),
            machine_code,
            msign,
            token,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp,
            printName);
    }

    /**
     * 极速授权
     */
    @Override
    public String speedAu(String machine_code, String qr_key) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .speedAu(eLinkConfig.getClientId(), machine_code, qr_key, "all", ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 打印
     */
    @Override
    public String print(String token, String machine_code, String content, String origin_id) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.print(eLinkConfig.getClientId(),
            token,
            machine_code,
            content,
            origin_id,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp);
    }

    /**
     * 删除终端授权
     */
    @Override
    public String deletePrinter(String token, String machine_code) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .deletePrinter(eLinkConfig.getClientId(), token, machine_code, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 添加应用菜单
     */
    @Override
    public String addPrintMenu(String token, String machine_code, String content) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .addPrintMenu(eLinkConfig.getClientId(), token, machine_code, content, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 关机重启接口
     */
    @Override
    public String shutDownRestart(String token, String machine_code, String response_type) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.shutDownRestart(eLinkConfig.getClientId(),
            token,
            machine_code,
            response_type,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp);
    }

    /**
     * 声音调节
     */
    @Override
    public String setSound(String token, String machine_code, String response_type, String voice) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.setSound(eLinkConfig.getClientId(),
            token,
            machine_code,
            response_type,
            voice,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp);
    }

    /**
     * 获取机型打印宽度接口
     */
    @Override
    public String getPrintInfo(String token, String machine_code) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .getPrintInfo(eLinkConfig.getClientId(), token, machine_code, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 获取机型软硬件版本接口
     */
    @Override
    public String getVersion(String token, String machine_code) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.getVersion(eLinkConfig.getClientId(), token, machine_code, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 取消所有未打印订单
     */
    @Override
    public String cancelAll(String token, String machine_code) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.cancelAll(eLinkConfig.getClientId(), token, machine_code, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 取消单条未打印订单
     */
    @Override
    public String cancelOne(String token, String machine_code, String order_id) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .cancelOne(eLinkConfig.getClientId(), token, machine_code, order_id, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 设置logo
     */
    @Override
    public String setIcon(String token, String machine_code, String img_url) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI
            .setIcon(eLinkConfig.getClientId(), token, machine_code, img_url, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 删除logo
     */
    @Override
    public String deleteIcon(String token, String machine_code) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.deleteIcon(eLinkConfig.getClientId(), token, machine_code, ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp), ELinkAPI.getuuid(), timestamp);
    }

    /**
     * 打印方式
     */
    @Override
    public String btnPrint(String token, String machine_code, String response_type) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.btnPrint(eLinkConfig.getClientId(),
            token,
            machine_code,
            response_type,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp);
    }

    /**
     * 接单拒单设置接口
     */
    @Override
    public String getOrder(String token, String machine_code, String response_type) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return ELinkAPI.getOrder(eLinkConfig.getClientId(),
            token,
            machine_code,
            response_type,
            ELinkAPI.getSin(eLinkConfig.getClientId(), eLinkConfig.getClientSecret(), timestamp),
            ELinkAPI.getuuid(),
            timestamp);
    }

}
