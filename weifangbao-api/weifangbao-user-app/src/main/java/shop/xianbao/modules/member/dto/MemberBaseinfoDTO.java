package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 会员信息表
 * 新增/详情/修改接口的DTO
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@ApiModel(value = "会员信息表")
public class MemberBaseinfoDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    // 以下注释看完删除:
    // 根据自己的业务需求加上 hibernate validator 注解
    // 校验字符类型用 --> @NotBlank(message = "{xxx.xxx.xx}", groups = {AddGroup.class, UpdateGroup.class})
    // 校验数字类型用 --> @NotNull(message = "{xxx.xxx.xx}", groups = {AddGroup.class, UpdateGroup.class})
    // 详情请参考https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "用户头像")
    private String userHeadimg;

    @ApiModelProperty(value = "用户等级Id")
    private Long memberLevel;

    @ApiModelProperty(value = "是否是系统后台用户 0 不是 1 是")
    private Integer isSystem;

    @ApiModelProperty(value = "是否是前台会员")
    private Integer isMember;

    @ApiModelProperty(value = "联系手机号")
    private String mobile;

    @ApiModelProperty(value = "手机号是否绑定 0 未绑定 1 绑定 ")
    private Integer userTelBind;

    @ApiModelProperty(value = "qq号")
    private String userQq;

    @ApiModelProperty(value = "qq互联id")
    private String qqOpenid;

    @ApiModelProperty(value = "qq账号相关信息")
    private String qqInfo;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "是否邮箱绑定")
    private Integer userEmailBind;

    @ApiModelProperty(value = "微信用户openid")
    private String wxOpenid;

    @ApiModelProperty(value = "微信用户是否关注")
    private Integer wxIsSub;

    @ApiModelProperty(value = "微信用户信息")
    private String wxInfo;

    @ApiModelProperty(value = "附加信息")
    private String otherInfo;

    @ApiModelProperty(value = "登录次数")
    private Integer loginNum;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "住址")
    private String location;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "微信unionid")
    private String wxUnionid;

    @ApiModelProperty(value = "模板id")
    private Integer qrcodeTemplateId;

    @ApiModelProperty(value = "微信用户关注时间")
    private Integer wxSubTime;

    @ApiModelProperty(value = "微信用户取消关注时间")
    private Integer wxNotsubTime;

    @ApiModelProperty(value = "生日")
    private Integer birthday;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}