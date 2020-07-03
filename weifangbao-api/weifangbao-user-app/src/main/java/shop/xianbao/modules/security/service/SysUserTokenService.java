/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.security.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.security.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

    /**
     * 生成token
     * @param userId  用户ID
     */
    Result createToken(Long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(Long userId);

}