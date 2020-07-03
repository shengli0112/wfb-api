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
 * 会员验证信息表
 * 新增/详情/修改接口的DTO
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@ApiModel(value = "会员验证信息表")
public class MemberDTO extends XianbaoBaseDTO {
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

    @ApiModelProperty(value = "实例信息id")
    private Long instanceId;

    @ApiModelProperty(value = "用户名(默认手机号)")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "随机值")
    private String salt;

    @ApiModelProperty(value = "用户密码，密码+随机值（MD5）")
    private String password;

    @ApiModelProperty(value = "用户状态: -1黑名单 0冻结 1正常")
    private Integer status;

    @ApiModelProperty(value = "账号id是否修改过 0:未修改 1:已修改")
    private Integer editStatus;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}