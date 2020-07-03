/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.dao.UserDao;
import shop.xianbao.entity.UserEntity;
import shop.xianbao.modules.member.dto.LoginDTO;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.service.TokenService;
import shop.xianbao.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public
class UserServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements UserService{
    @Autowired
    private TokenService tokenService;
    
    @Override
    public
    UserEntity getByMobile(String mobile){
        return baseDao.getUserByMobile(mobile);
    }
    
    @Override
    public
    UserEntity getUserByUserId(Long userId){
        return baseDao.getUserByUserId(userId);
    }
    
    @Override
    public
    Map<String, Object> login(LoginDTO dto){
        UserEntity user = getByMobile(dto.getUsername());
        AssertUtils.isNull(user, ErrorCode.ACCOUNT_PASSWORD_ERROR);
        
        //密码错误
        if(!user.getPassword().equals(DigestUtils.sha256Hex(dto.getPassword()))){
            throw new XianbaoException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }
        
        //获取登录token
        MemberTokenEntity tokenEntity = tokenService.createToken(user.getId(), dto.getClient(),dto.getTerminalId());
        
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireDate().getTime()-System.currentTimeMillis());
        
        return map;
    }
    
}