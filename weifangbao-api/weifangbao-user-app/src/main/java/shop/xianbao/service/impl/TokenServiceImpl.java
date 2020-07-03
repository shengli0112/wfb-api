/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.Result;
import shop.xianbao.dao.MemberTokenDao;
import shop.xianbao.modules.member.entity.MemberTokenEntity;
import shop.xianbao.service.TokenService;

import java.util.Date;
import java.util.UUID;


@Service
public
class TokenServiceImpl extends BaseServiceImpl<MemberTokenDao, MemberTokenEntity> implements TokenService{

    @Autowired
    private MemberTokenDao memberTokenDao;
    
    @Override
    public
    MemberTokenEntity getByToken(String token){
        return baseDao.getByToken(token);
    }
    
    @Override
    public
    MemberTokenEntity createToken(Long userId, int client,String terminalId){

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime()+EXPIRE_IN*1000);
        
        //用户token
        String token;
        
        //判断是否生成过token
        MemberTokenEntity tokenEntity = baseDao.getByMIdClient(userId, client);
        if(tokenEntity == null){
            //生成一个token
            token = generateToken();
            
            tokenEntity = new MemberTokenEntity();
            tokenEntity.setMid(userId);
            tokenEntity.setToken(token);
            tokenEntity.setClientType(client);
            tokenEntity.setExpireDate(expireTime);
            tokenEntity.setTerminalId(terminalId);
            
            //保存token
            this.insert(tokenEntity);
        }else{
            //判断token是否过期
            /*if(tokenEntity.getExpireDate().getTime()<System.currentTimeMillis()){
                //token过期，重新生成token
                token = generateToken();
            }else{
                token = tokenEntity.getToken();
            }*/
            //重新生成token
            token = generateToken();
            tokenEntity.setToken(token);
            tokenEntity.setClientType(client);
            tokenEntity.setExpireDate(expireTime);
            tokenEntity.setTerminalId(terminalId);
            
            //更新token
            this.updateById(tokenEntity);
        }
        
        return tokenEntity;
    }
    
    @Override
    public
    void expireToken(String token){
        QueryWrapper<MemberTokenEntity> wrapper = new QueryWrapper<MemberTokenEntity>();
        wrapper.eq("token", token);
        baseDao.delete(wrapper);
    }
    
    private
    String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 更新token表 设备终端id
     * @param token
     * @return
     */
    @Override
    public Result updateTerminalId(MemberTokenEntity token) {
        //取得当前token
        memberTokenDao.updateTerminalId(token);
        return new Result();
    }
}
