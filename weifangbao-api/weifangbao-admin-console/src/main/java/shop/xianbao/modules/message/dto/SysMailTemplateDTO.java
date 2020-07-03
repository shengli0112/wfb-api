/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * 邮件模板
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@ApiModel(value = "邮件模板")
public class SysMailTemplateDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "模板名称")
    @NotBlank(message = "{mail.name.require}", groups = DefaultGroup.class)
    private String name;

    @ApiModelProperty(value = "邮件主题")
    @NotBlank(message = "{mail.subject.require}", groups = DefaultGroup.class)
    private String subject;

    @ApiModelProperty(value = "邮件正文")
    @NotBlank(message = "{mail.content.require}", groups = DefaultGroup.class)
    private String content;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

}