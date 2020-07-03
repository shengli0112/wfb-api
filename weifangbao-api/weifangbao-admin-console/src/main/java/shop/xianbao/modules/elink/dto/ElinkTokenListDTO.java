package shop.xianbao.modules.elink.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 易联云平台访问令牌表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@ApiModel(value = "易联云平台访问令牌表-列表")
public class ElinkTokenListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "id")
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
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "更新者id")
    private Long updater;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}