/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.NoLogin;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author Tim tim@ruitukeji.com
 */
@Component
public
class AuthorizationInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private TokenService tokenService;

    public static final String USER_KEY = "userId";
    public static final String USER_TOKEN = "userToken";

    @Override
    public
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Login needAccessToken;
        NoLogin noNeedAccessToken;
        if(handler instanceof HandlerMethod){
            needAccessToken = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
            noNeedAccessToken = ((HandlerMethod) handler).getMethodAnnotation(NoLogin.class);
        }else{
            return true;
        }

        if (needAccessToken == null && noNeedAccessToken == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if( noNeedAccessToken != null && org.apache.commons.lang3.StringUtils.isBlank(token)){
            return true;
        }

        //token为空
        if(StringUtils.isBlank(token)){
            throw new XianbaoException(ErrorCode.REQUEST_ACCESS_NO_TOKEN);
        }

        //查询token信息
        MemberTokenEntity tokenEntity = tokenService.getByToken(token);
        if(tokenEntity == null){
            throw new XianbaoException(ErrorCode.REQUEST_ACCESS_TOKEN_INVALID);
        }
        if(tokenEntity.getExpireDate().getTime()<System.currentTimeMillis()){
            throw new XianbaoException(ErrorCode.REQUEST_ACCESS_TOKEN_EXPIRED);
        }

        //设置userId到request里，后续根据userId，获取用户信息
//        request.setAttribute(USER_KEY, tokenEntity.getMid());
        request.setAttribute(USER_TOKEN, tokenEntity);

        return true;
    }
}
