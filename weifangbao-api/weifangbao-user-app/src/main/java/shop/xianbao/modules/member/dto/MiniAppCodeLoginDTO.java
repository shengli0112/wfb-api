package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.NotBlank;

/**
 * @program: MiniAppLoginDTO
 * @description: 微信小程序登陆DTO
 * @author: yh
 * @create: 2019-09-03 17:26
 **/
@Data
public class MiniAppCodeLoginDTO {
    @NotBlank(message = "{common.code.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "临时登录凭证code")
    private String code;

    //    @NotBlank(message = "{common.rawData.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "用户非敏感信息")
    private String rawData;

    //    @NotBlank(message = "{common.signature.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "签名")
    private String signature;

    //    @NotBlank(message = "{common.encryptedData.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "用户敏感信息")
    private String encryptedData;

    //    @NotBlank(message = "{common.iv.require}", groups = {AddGroup.class})
    @ApiModelProperty(value = "解密算法的向量")
    private String iv;

}
