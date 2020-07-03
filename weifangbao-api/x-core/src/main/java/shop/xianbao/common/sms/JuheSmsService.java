package shop.xianbao.common.sms;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.utils.UrlEncoderUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.SmsService;
import shop.xianbao.common.utils.HttpUtil;
import shop.xianbao.common.utils.SpringContextUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: JuheSmsService
 * @description: 聚合数据短信接口
 * @author: yh
 * @create: 2019-11-28 15:00
 **/
public class JuheSmsService extends AbstractSmsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    public static final String DEF_CHATSET = "UTF-8";

    private final String DOMAIN = "http://v.juhe.cn/sms/send";

    private static final int DEF_CONN_TIMEOUT = 30000;

    private static final int DEF_READ_TIMEOUT = 30000;

    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    private SmsConfig config;

    private final String appKey;

    public JuheSmsService(SmsConfig config) {
        this.config = config;
        appKey = this.config.getJuheAppKey();
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params) {
        sendSms(mobile, params, null);
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params, String template) {
        sendSms(mobile, params, null, template);
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params, String signName, String template) {
        String result = null;
        //请求接口地址
        String url = "http://v.juhe.cn/sms/send";
        //请求参数
        HashMap<String, String> requestParams = new HashMap(5);
        //接收短信的手机号码
        requestParams.put("mobile", mobile);
        //短信模板ID，请参考个人中心短信模板设置
        requestParams.put("tpl_id", template);
        //变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        requestParams.put("tpl_value", urlencode(params));
        //应用APPKEY(应用详细页查询)
        requestParams.put("key", this.appKey);
        //返回数据的格式,xml或json，默认json
        requestParams.put("dtype", "");

        try {
            result = HttpUtil.sendPost(url, requestParams, false);
            log.info("短信发送结果：{}", result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        int status;
        JSONObject object = JSONObject.parseObject(result);
        String reason = object.getString("reason");
        Integer errorCode = object.getInteger("error_code");
        if (errorCode == 0) {
            status = Constant.SUCCESS;
        } else {
            status = Constant.FAIL;
            log.info("短信发送失败,错误码:{},错误原因:{}", errorCode, reason);
        }
        //保存短信记录
        SmsService sysSmsService = SpringContextUtils.getBean(SmsService.class);
        sysSmsService.save(Constant.SmsService.JUHE.getValue(), mobile, params, status);

        if (status == Constant.FAIL) {
            throw new XianbaoException(ErrorCode.SEND_SMS_ERROR, reason);
        }
    }

    //1.屏蔽词检查测
    public void getRequest1() {
        String result = null;
        //请求接口地址
        String url = "http://v.juhe.cn/sms/black";
        //请求参数
        Map<String, String> params = new HashMap(2);
        //需要检测的短信内容，需要UTF8 URLENCODE
        params.put("word", "");
        //应用APPKEY(应用详细页查询)
        params.put("key", this.appKey);

        try {
            result = HttpUtil.sendPost(url, params, false);
            JSONObject object = JSONObject.parseObject(result);
            if (object.getInteger("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将map型转为请求参数型
     */
    private static String urlencode(Map<String, String> data) {
        if (MapUtils.isEmpty(data)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            sb.append("#").append(i.getKey()).append("#").append("=").append(i.getValue() + "").append("&");
        }
        if (StringUtils.isNotBlank(sb)) {
            String result = sb.toString().substring(0, sb.length() - 1);
            return UrlEncoderUtils.encode(result);
        } else {
            return "";
        }
    }
}
