package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户实名认证表
 * 新增/详情/修改接口的DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Data
@ApiModel(value = "用户实名认证表")
public class UserAuthCheckDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = AddGroup.class)
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull(groups = AddGroup.class)
    @Range(min = 2, max = 3, groups = AddGroup.class, message = "status值非法")
    @ApiModelProperty(value = "审核状态 0申请中 1待审核 2审核通过 3审核驳回")
    private Integer status;

    @ApiModelProperty(value = "审核备注")
    private String remark;

}