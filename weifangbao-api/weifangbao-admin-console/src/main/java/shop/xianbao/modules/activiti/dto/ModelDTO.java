/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.activiti.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import javax.validation.constraints.NotBlank;

/**
 * 模型
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Data
@Api(tags = "模型")
public class ModelDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模型名称")
    @NotBlank(message = "{model.name.require}")
    private String name;

    @ApiModelProperty(value = "模型标识")
    @NotBlank(message = "{model.key.require}")
    private String key;

    @ApiModelProperty(value = "模型描述")
    private String description;

}