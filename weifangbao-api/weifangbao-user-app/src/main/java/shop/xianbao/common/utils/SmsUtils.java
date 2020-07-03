package shop.xianbao.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信处理
 *
 * @author wdp
 *         Created by Ruitukeji on 2018/12/26.
 */
public class SmsUtils {

    /**
     * 短信发送
     *
     * @param mobile 手机号
     * @param captcha 验证码
     * @return 发送是否成功
     */
    public static boolean sendCaptcha(String mobile, String captcha) {
        //TODO 设置为从配置文件获取
        String url = "http://message.dev.cosmoplat.com/message/sendmessage";
        /* 失效时间 */
        String expireTime = "1";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            Map map = new HashMap();
            map.put("code", captcha);
            map.put("hour", expireTime);

            JSONObject object = new JSONObject();
            object.put("user", 1);
            object.put("receiver", mobile);
            object.put("messageType", "BDSMS_CODE");
            object.put("params", map);
            System.out.println(object);

            //请求头
            post.setHeader("Content-Type", "application/json");
            //构造请求实体类
            StringEntity entity = new StringEntity(object.toString(), Charset.forName("UTF-8"));
            post.setEntity(entity);
            //发短信
            HttpResponse response = httpClient.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                //获取返回值
                HttpEntity smsEntity = response.getEntity();
                String sms = EntityUtils.toString(smsEntity);
                JSONObject json = JSONObject.fromObject(sms);
                String success = json.getString("result");
                if (success.equals("ok")) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
