/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单
 *
 * @author Will
 */
@Data
public class TokenRetDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    /**
     * TODO
     * 在授权自动续期步骤中，获取新的token时需要提供的参数。
     */
    private String refreshToken;

    /**
     * token的有效期，单位为秒。
     */
    private int expiresIn;
}