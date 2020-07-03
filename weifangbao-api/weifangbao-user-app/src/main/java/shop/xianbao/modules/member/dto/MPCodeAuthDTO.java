package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.NotBlank;

/**
 * @program: MiniAppMobileLoginDTO
 * @description: 微信小程序登陆DTO
 * @author: yh
 * @create: 2019-09-03 17:26
 **/
@Data
public class MPCodeAuthDTO {

    @NotBlank(message = "{common.redirectUri.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "redirectUri")
    private String redirectUri;

    private String scope;

    private String state;
}
