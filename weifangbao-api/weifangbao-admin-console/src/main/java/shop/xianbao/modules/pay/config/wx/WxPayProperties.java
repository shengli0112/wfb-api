package shop.xianbao.modules.pay.config.wx;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * wxpay config properties
 *
 * @author yanghuan
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.wx")
public class WxPayProperties {

    /**
     * 是否开启沙箱环境
     */
    private Boolean dev;

    private String appSecret;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    /**
     * 微信支付回调地址
     */
    private String notifyUrl;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
