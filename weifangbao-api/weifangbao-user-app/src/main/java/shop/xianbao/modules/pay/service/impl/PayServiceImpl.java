package shop.xianbao.modules.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dto.UnionUserDTO;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.pay.DTO.PayParamDTO;
import shop.xianbao.modules.pay.config.wx.WxMpUtils;
import shop.xianbao.modules.pay.service.PayService;
import shop.xianbao.modules.pay.strategy.PayContextStrategy;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @program: AliPayServiceImpl
 * @description:
 * @author: yh
 * @create: 2019-08-16 16:38
 **/
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayContextStrategy payContextStrategy;

    @Autowired
    private PayTypeService payTypeService;

    @Autowired
    private UnionUserService unionUserService;

    @Override
    public Object orderPay(PayParamDTO payParamDTO) {
        return null;
    }

    private void checkOpenId(PayParamDTO payParamDTO, Long userId) {
        if (StringUtils.isEmpty(payParamDTO.getOpenId())) {
            //参数中openId为空时，获取用户绑定openId
            UnionUserDTO unionUserDTO = unionUserService.get(userId);
            if (unionUserDTO != null && StringUtils.isNotEmpty(unionUserDTO.getOpenId())) {
                payParamDTO.setOpenId(unionUserDTO.getOpenId());
            } else {
                throw new XianbaoException("未绑定手机号");
            }
        }
    }

    @Override
    public Object groupPay(PayParamDTO payParamDTO) {
        return null;
    }

    @Override
    public Result getWxConfig(String returnUrl) throws WxErrorException {
        Result result = new Result();
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeByCode("wechat_pay", "JSAPI_FWH");
        if (Objects.equals(null, payTypeEntity)) {
            result.error("未配置微信公众号支付");
        }

        WxMpService wxMpService = WxMpUtils.getWxMpService(payTypeEntity.getConfig());
        JSONObject jsonObject = JSONObject.parseObject(payTypeEntity.getConfig());
        //        String accessToken = wxMpService.getAccessToken();
        String jsapiTicket = wxMpService.getJsapiTicket();
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String timestamp = "" + System.currentTimeMillis() / 1000;
        Map<String, String> params = new HashMap<>();
        params.put("jsapi_ticket", jsapiTicket);
        params.put("noncestr", noncestr);
        params.put("timestamp", timestamp);
        params.put("url", returnUrl);
        //1.1 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）
        Map<String, String> sortParams = sortAsc(params);
        //1.2 使用URL键值对的格式拼接成字符串
        String str = mapJoin(sortParams, false);
        String signature = DigestUtils.sha1Hex(str);
        Map<String, String> config = new HashMap<>();
        config.put("debug", "false");
        config.put("appId", jsonObject.getString("appId"));
        config.put("nonceStr", noncestr);
        config.put("timestamp", timestamp);
        config.put("signature", signature);
        return result.ok(config);
    }

    private HashMap<String, String> sortAsc(Map<String, String> map) {
        HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            tempMap.put(item.getKey(), item.getValue());
        }
        return tempMap;
    }

    public static String mapJoin(Map<String, String> map, boolean valueUrlEncode) {
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            if (map.get(key) != null && !"".equals(map.get(key))) {
                try {
                    String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
                    sb.append(temp);
                    sb.append("=");
                    //获取到map的值
                    String value = map.get(key);
                    //判断是否需要url编码
                    if (valueUrlEncode) {
                        value = URLEncoder.encode(map.get(key), "utf-8").replace("+", "%20");
                    }
                    sb.append(value);
                    sb.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
