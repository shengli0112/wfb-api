/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录表单
 *
 * @author Will
 */
@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{common.field.require}:username")
    private String username;

    @NotBlank(message = "{common.field.require}:password")
    private String password;

    @NotNull(message = "{common.field.require}:client")
    @Min(value = 1, message = "{common.field.illegal}:client")
    @Max(value = 4, message = "{common.field.illegal}:client")
    private Integer client;

    //最后登录设备终端id
    private String terminalId;

}