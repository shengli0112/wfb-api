package shop.xianbao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
        System.out.printf("Server is running at http://localhost:%s/%s \n", serverPort, contextPath);
    }
}
