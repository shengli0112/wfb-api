/**
 /**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 审核消息DTO
 *
 */
@Data
public class CheckDTO{
    
    /**
     * 审核对象
     */
    @NotNull(message = "{common.field.require}:id")
    private Long id;
    
    /**
     * 审核状态
     */
    @NotNull(message = "{common.field.require}:status")
    private Integer status;
    
    /**
     * 原因
     */
    private String cause;




    
  

}