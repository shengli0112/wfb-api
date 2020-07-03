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

/**
 * 注册表单
 *
 * @author WILL
 */
@Data
public class RegisterDTO {
    @NotBlank(message = "{common.field.require}:mobile")
    private String mobile;

    @NotBlank(message = "{common.field.require}:captcha")
    private String captcha;

    //@NotBlank(message = "{common.field.require}:password")
    private String password;

    @NotNull(message = "{common.field.require}:client")
    @Min(value = 1, message = "{common.field.illegal}:client")
    @Max(value = 4, message = "{common.field.illegal}:client")
    private Integer client;

    //邀请码
    private String inviteCode;

    private String openId;

    //上一级id
    private Long pId;

    //上二级id
    private Long gId;

    //邀请码是否正确 (false 不正确)
    private Boolean isRight;
}
