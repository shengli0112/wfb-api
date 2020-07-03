/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 业务基础实体类，所有实体都需要继承
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
public abstract
class XianbaoBaseDTO implements Serializable{

//    @ApiModelProperty(value = "ID")
//    @Null(message = "{id.null}", groups = {AddGroup.class})
//    @NotNull(message = "{id.require}", groups = {UpdateGroup.class})
    private Long id;
}