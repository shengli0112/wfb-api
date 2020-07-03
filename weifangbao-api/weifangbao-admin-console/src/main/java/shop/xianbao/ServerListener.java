package shop.xianbao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import shop.xianbao.common.utils.IpUtils;

@Component
public
class ServerListener implements ApplicationListener<WebServerInitializedEvent>{
    
    @Value("${server.port}")
    private int port;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;
    
    @Override
    public
    void onApplicationEvent(WebServerInitializedEvent event){
        int serverPort = event.getWebServer().getPort();
        String ip = IpUtils.getLocalFirstIpv4();
        System.out.printf("Server is running at http://%s:%s%s \n", ip, serverPort, contextPath);
        System.out.printf("Swagger is running at http://%s:%s%s/swagger-ui.html \n", ip, serverPort, contextPath);
    }
}
