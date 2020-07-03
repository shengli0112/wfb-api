/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberTokenEntity;

/**
 * 用户Token
 *
 * @author Tim tim@ruitukeji.com
 */
public
interface TokenService extends BaseService<MemberTokenEntity>{
    
    /**
     *有效期 12小时
     */
    int EXPIRE_IN = 3600*12;
    
    MemberTokenEntity getByToken(String token);
    
    /**
     * 生成token
     * @param userId  用户ID,client 设备类型,terminalId 最后登录设备id
     * @return 返回token信息
     */
    MemberTokenEntity createToken(Long userId,int client,String terminalId);
    
    /**
     * 设置token过期
     * @param token 用户ID
     */
    void expireToken(String token);

    /**
     * 更新token表 设备终端id
     * @param token
     * @return
     */
    Result updateTerminalId(MemberTokenEntity token);
}
