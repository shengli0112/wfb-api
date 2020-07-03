/**
 /**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发送验证码
 *
 */
@Data
public class SendCaptchaDTO{
    
    /**
     * 手机号/邮箱
     */
    @NotBlank(message="receiver can not be blank")
    private String receiver;
    
    /**
     * 验证码用途
     */
    @NotNull(message="opt can not be empty")
    private Integer opt;
    
    /**
     * 图像验证码序号
     */
     @NotBlank(message="ivId can not be empty")
    private String ivId;

    /**
     * 图像验证码
     */
     @NotBlank(message="ivCode can not be empty")
    private String ivCode;
    
  

}