package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员用户信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "会员用户信息表")
public class MemberUserDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String userHeadimg;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户状态: -1黑名单 0冻结 1正常")
    private Integer status;

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

    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    @ApiModelProperty(value = "生日")
    private Long birthday;

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

    @ApiModelProperty(value = "会员等级")
    private Long memberLevel;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "积分")
    private Integer point;
}