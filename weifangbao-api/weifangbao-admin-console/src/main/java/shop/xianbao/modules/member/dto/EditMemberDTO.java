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
public class EditMemberDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户状态: -1黑名单 0冻结 1正常")
    private Integer status;

    /**
     * 0:未修改 1:已修改
     */
    @ApiModelProperty(value = "账号id是否修改过")
    private Integer editStatus;

}