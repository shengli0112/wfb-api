/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service.impl;

import org.springframework.stereotype.Service;
import shop.xianbao.service.HttpService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 发送短信
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Service
public
class HttpServiceImpl implements HttpService{

    @Override
    public
    void downloadFile(File file, HttpServletResponse response) throws IOException{
        if(!file.exists()){
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br  = new BufferedInputStream(new FileInputStream(file));
        byte[]              buf = new byte[1024];
        int                 len = 0;

        response.reset(); // 非常重要
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        OutputStream out = response.getOutputStream();
        while((len = br.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
    }
}