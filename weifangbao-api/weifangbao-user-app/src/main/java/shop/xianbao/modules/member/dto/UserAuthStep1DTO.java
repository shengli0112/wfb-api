package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;

import javax.validation.constraints.Null;

/**
 * 用户实名认证表
 * 新增/详情/修改接口的DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Data
@ApiModel(value = "用户实名认证表")
public class UserAuthStep1DTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    private Long id;

    @ApiModelProperty(value = "union_id")
    private Long unionId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "身份证号")
    //    @Pattern(message="身份证格式不正确",regexp = Constants.IdNumber, groups = {AddGroup.class, UpdateGroup.class})
    private String idNumber;

}