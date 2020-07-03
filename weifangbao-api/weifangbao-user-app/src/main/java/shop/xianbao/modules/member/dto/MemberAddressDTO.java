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
 * 用户收货地址表
 * 新增/详情/修改接口的DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
@Data
@ApiModel(value = "用户收货地址表")
public class MemberAddressDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "唯一用户ID union_user.id")
    private Long unionId;

    @ApiModelProperty(value = "member.id")
    private Long memberId;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "国家")
    private Long country;

    @ApiModelProperty(value = "省份")
    private Long province;

    @ApiModelProperty(value = "城市")
    private Long city;

    @ApiModelProperty(value = "地区")
    private Long district;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipcode;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "默认收货地址 0否 1是")
    private Integer isDefault;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

}