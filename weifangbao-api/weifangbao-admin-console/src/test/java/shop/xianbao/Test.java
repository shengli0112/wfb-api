/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public
class Test{
    
    
    @org.junit.Test
    public
    void testJpush(){
        String APP_KEY       = "f5f9d3fd56aeedf6574718cc";
        String MASTER_SECRET = "9081f6a8a1746e2cffa0e2d3";
        
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_alias_alert();
        
        try{
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - "+result);
            
        }catch(APIConnectionException e){
            // Connection error, should retry later
            System.out.println("Connection error, should retry later "+e.getMessage());
            
        }catch(APIRequestException e){
            // Should review the error, and fix the request
            System.out.println("[ERROR] Should review the error, and fix the request"+e.getMessage());
            System.out.println("[INFO] HTTP Status: "+e.getStatus());
            System.out.println("[INFO] Error Code: "+e.getErrorCode());
            System.out.println("[INFO] Error Message: "+e.getErrorMessage());
        }
    }
    
    public static
    PushPayload buildPushObject_all_all(String alter){
        return new PushPayload.Builder().setPlatform(Platform.all()).setAudience(Audience.all())
                                        .setNotification(Notification.alert(alter)).build();
    }
    
    public static
    PushPayload buildPushObject_all_alias_alert(){
        //  alert field is needed. It can be empty string.;
        String str   = RandomStringUtils.randomAlphabetic(1024);
        String str2   = RandomStringUtils.randomAlphabetic(1024);
        String title = "以下省略1024字"+str;
        String msg   = "以下也省略1024字"+str;;
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
                          .setNotification(Notification.android(msg, title, null)).build();
    }
}