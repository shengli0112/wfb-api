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
public class MPCodeLoginDTO {

    @NotNull(message = "终端类型不能为空", groups = {AddGroup.class})
    private Integer terminal;

    @NotBlank(message = "{common.code.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "临时登录凭证code")
    private String code;

    private String state;
}
