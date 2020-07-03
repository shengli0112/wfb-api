/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.constants.Constants;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.DefaultGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 修改登录注册信息表单
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@ApiModel(value = "修改登录注册信息表单")
public class EditLoginInfoDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "{sysuser.mobile.require}", groups = DefaultGroup.class)
    @Pattern(message = "{member.mobile.error}", regexp = Constants.MobilePattern, groups = {AddGroup.class, UpdateGroup.class})
    private String mobile;

    @ApiModelProperty(value = "旧密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String newPassword;

    //验证码
    private String captcha;

}