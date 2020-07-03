/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.interceptor.AuthorizationInterceptor;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.service.TokenService;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author Tim tim@ruitukeji.com
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberDataEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object userToken = request.getAttribute(AuthorizationInterceptor.USER_TOKEN, RequestAttributes.SCOPE_REQUEST);
        if (userToken == null) {
            return null;
        }
        MemberTokenEntity memberToken = (MemberTokenEntity)userToken;
        Long userId = memberToken.getMid();
//        Object userId = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(userId == null){
            return null;
        }

        //获取用户信息
        MemberDataEntity user = memberService.getUserDataByMId(userId);
        user.setToken(memberToken);
        return user;
    }
}
