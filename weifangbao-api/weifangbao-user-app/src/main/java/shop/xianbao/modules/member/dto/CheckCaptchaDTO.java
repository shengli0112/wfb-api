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
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 验证码验证表单
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@ApiModel(value = "验证码验证表单")
public class CheckCaptchaDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新手机号", required = true)
    @Pattern(message = "{member.mobile.error}", regexp = Constants.MobilePattern, groups = {AddGroup.class, UpdateGroup.class})
    private String newMobile;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "{sysuser.captcha.require}")
    private String captcha;

    /**
     * false:不更新 true：更新
     * 默认不更新
     */
    @ApiModelProperty(value = "是否更新该手机号")
    private boolean isUpdate = false;

    //    @ApiModelProperty(value = "旧手机号")
    //    @Pattern(message = "{member.mobile.error}", regexp = Constants.MobilePattern, groups = {AddGroup.class, UpdateGroup.class})
    //    private String oldMobile;
}