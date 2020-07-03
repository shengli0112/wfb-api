package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * 系统用户表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "系统用户表")
public class EditMemberUserDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long uid;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "真实名字")
    private String realname;

    @ApiModelProperty(value = "用户头像")
    private String userHeadimg;

    @ApiModelProperty(value = "联系手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "生日")
    private Integer birthday;

    @ApiModelProperty(value = "会员等级")
    private Long memberLevel;
}