package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: MiniAppMobileLoginDTO
 * @description: 微信小程序登陆DTO
 * @author: yh
 * @create: 2019-09-03 17:26
 **/
@Data
public class MPBindMobileDTO {

    @NotNull(message = "{common.ternimal.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "terminal")
    private Integer terminal;

    @NotBlank(message = "{common.code.require}", groups = {AddGroup.class})
    private String openId;

    @NotBlank(message = "{common.mobile.require}", groups = {AddGroup.class})
    private String mobile;

    @NotBlank(message = "{common.captcha.require}", groups = {AddGroup.class})
    private String captcha;
}
