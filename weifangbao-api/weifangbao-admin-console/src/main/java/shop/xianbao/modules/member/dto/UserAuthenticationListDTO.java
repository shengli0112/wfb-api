package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.enums.UserAuthStatusEnums;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 用户实名认证表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Data
@ApiModel(value = "用户实名认证表-列表")
public class UserAuthenticationListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "union_id")
    private Long unionId;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @NotNull(message = "{common.id.require}", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @NotNull(message = "{common.id.require}", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "门店地址")
    private String companyAddress;

    @NotNull(message = "{common.id.require}", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @NotNull(message = "{common.id.require}", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "门头照")
    private String companyPhoto;

    @ApiModelProperty(value = "审核状态 0申请中 1待审核 2认证通过 3认证失败")
    private Integer status;

    @ApiModelProperty(value = "审核状态 0申请中 1待审核 2认证通过 3认证失败")
    private String statusName;

    public String getStatusName() {
        if (status != null) {
            return EnumUtil.getEnumBycode(UserAuthStatusEnums.class, status).getName();
        } else {
            return null;
        }
    }

}