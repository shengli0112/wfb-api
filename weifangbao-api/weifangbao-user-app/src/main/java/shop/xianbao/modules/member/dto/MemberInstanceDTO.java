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
 * 会员实例信息表
 * 新增/详情/修改接口的DTO
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@ApiModel(value = "会员实例信息表")
public class MemberInstanceDTO extends XianbaoBaseDTO {
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

    @ApiModelProperty(value = "点亮的用户类型统计数")
    private Integer count;

    @ApiModelProperty(value = "0:未开通 1:开通")
    private Integer buyer;

    @ApiModelProperty(value = "0:未开通 1:开通")
    private Integer seller;

    @ApiModelProperty(value = "0:未开通 1:开通")
    private Integer distribution;

}