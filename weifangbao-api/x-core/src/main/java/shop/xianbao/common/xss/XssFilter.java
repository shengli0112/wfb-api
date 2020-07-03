/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.xss;

import shop.xianbao.common.utils.HttpContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * XSS过滤
 *
 * @author Tim tim@ruitukeji.com
 */
public
class XssFilter implements Filter{
    
    @Override
    public
    void init(FilterConfig config) throws ServletException{
    }
    
    @Override
    public
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest  hsRequest  = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;
        
        /** *************************全局允许跨域************************* */
        hsResponse.setHeader("Access-Control-Allow-Credentials", "true");
        hsResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        hsResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,PUT,DELETE");
        //resp.setHeader("Access-Control-Max-Age", "3600");
        //resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        hsResponse.setHeader("Access-Control-Allow-Headers", "TOKEN,KEY,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE,x-requested-with");
        if("OPTIONS".equals(hsRequest.getMethod())){
            hsResponse.setStatus(200);
            hsResponse.getWriter().write("OPTIONS returns OK");
            return;
        }
        
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(hsRequest);
        chain.doFilter(xssRequest, response);
    }
    
    @Override
    public
    void destroy(){
    }
    
}