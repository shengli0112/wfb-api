package shop.xianbao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: H5UrlConfig
 * @description:
 * @author: yh
 * @create: 2019-05-17 17:40
 **/
@Data
@Component
@ConfigurationProperties(prefix = "app-url")
public class H5UrlConfig {

    private String newsUrl;

    private String traceListUrl;

    private String traceDetailUrl;

}
