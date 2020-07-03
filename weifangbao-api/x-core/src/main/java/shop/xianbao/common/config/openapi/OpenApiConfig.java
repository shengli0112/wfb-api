package shop.xianbao.common.config.openapi;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: OpenApiConfig
 * @description: open_api服务器配置
 * @author: yh
 * @create: 2019-04-12 14:10
 **/
@Data
@Component
@ConfigurationProperties(prefix = "open-api")
public class OpenApiConfig {
    private String ip;

    private String port;

    private String path;

    private String key;

    public String getUrl(){
        return ip + ":" + port + "/" + (StringUtils.isNotBlank(path) ? path + "/" : "");
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
