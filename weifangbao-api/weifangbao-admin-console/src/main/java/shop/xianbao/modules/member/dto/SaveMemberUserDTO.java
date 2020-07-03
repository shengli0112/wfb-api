package shop.xianbao.modules.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.DefaultGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 系统用户表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "系统用户表")
public class SaveMemberUserDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "{sysuser.username.require}", groups = DefaultGroup.class)
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String userHeadimg;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "{sysuser.mobile.require}", groups = DefaultGroup.class)
    private String mobile;

    @ApiModelProperty(value = "随机值")
    private String salt;

    @ApiModelProperty(value = "用户密码，密码+随机值（MD5）")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "{sysuser.password.require}", groups = AddGroup.class)
    private String password;

    @ApiModelProperty(value = "用户状态: -1黑名单 0冻结 1正常")
    private Integer status;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "{sysuser.email.error}", groups = DefaultGroup.class)
    private String email;

    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "会员等级")
    private Long memberLevel;

    @ApiModelProperty(value = "等级名称")
    private String levelName;
}