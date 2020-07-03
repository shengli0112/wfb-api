package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改会员用户信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "修改用户信息表")
public class EditUserInfoDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String userHeadimg;

    @ApiModelProperty(value = "qq号")
    private String userQq;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "微信用户openid")
    private String wxOpenid;

    @ApiModelProperty(value = "微信unionid")
    private String wxUnionid;

    @ApiModelProperty(value = "真实名字")
    private String realName;

    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "紧急联系方式")
    private String contactNformation;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
}