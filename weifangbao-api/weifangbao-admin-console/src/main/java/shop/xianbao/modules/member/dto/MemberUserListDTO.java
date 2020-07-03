package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.modules.unionuser.enums.IsRealAuthEnums;

import java.util.Date;

/**
 * 会员用户信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "系统用户表")
public class MemberUserListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "真实名字")
    private String realname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户状态:  0：未认证  1：已认证")
    private Integer isRealAuth;

    @ApiModelProperty(value = "用户状态:  0：未认证  1：已认证")
    private String isRealAuthName;

    public String getIsRealAuthName() {
        return EnumUtil.getEnumBycode(IsRealAuthEnums.class, isRealAuth).getName();
    }

    @ApiModelProperty(value = "用户状态:  0：正常  1：停用")
    private Integer isLocked;

    @ApiModelProperty(value = "注册/创建时间")
    private Date createDate;

    private Long pId;
    private Long gId;

}