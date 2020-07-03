package shop.xianbao.modules.elink.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 易联云平台访问令牌表
 * 新增/详情/修改接口的DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@ApiModel(value = "易联云平台访问令牌表")
public class ElinkTokenDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "易联云应用id")
    private String clientId;

    @ApiModelProperty(value = "访问令牌，API调用时需要，令牌可以重复使用无失效时间")
    private String accessToken;

    @ApiModelProperty(value = "更新access_token所需，有效时间35天")
    private String refreshToken;

    @ApiModelProperty(value = "令牌的有效时间，单位秒 (30天),注：该模式下可忽略此参数")
    private Integer expiresIn;

    @ApiModelProperty(value = "权限")
    private String scope;

    @ApiModelProperty(value = "排序字段 小值在前")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}