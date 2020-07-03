package shop.xianbao.common.elink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: ELindConfig
 * @description: 易联云应用配置类
 * @author: yh
 * @create: 2019-05-16 13:52
 **/
@Data
@Component
@ConfigurationProperties(prefix = "elink")
public class ELinkConfig {

    /**
     * 易联云颁发给开发者的应用ID 非空值
     */
    private String clientId;

    /**
     * 易联云颁发给开发者的应用secret 非空值
     */
    private String clientSecret;

}
