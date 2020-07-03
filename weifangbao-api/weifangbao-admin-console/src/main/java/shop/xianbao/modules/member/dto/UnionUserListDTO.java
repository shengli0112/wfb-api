package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 基础用户表（登录验证）
 * 列表DTO
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Data
@ApiModel(value = "基础用户表（登录验证）-列表")
public class UnionUserListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "id 全局唯一表示union_id")
    private Long id;

    @ApiModelProperty(value = "用户名用于登录")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "加密盐值")
    private String salt;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别   0=未知  1=男   2=女  3=保密")
    private Integer gender;

    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    @ApiModelProperty(value = "身份证正面照片")
    private String idCardFront;

    @ApiModelProperty(value = "身份证背面照片")
    private String idCardBack;

    @ApiModelProperty(value = "手持身份证照片")
    private String idCardHand;

    @ApiModelProperty(value = "是否经过实名认证 0=否 1=是")
    private Integer isRealAuth;

    @ApiModelProperty(value = "是否可用  0：正常  1：停用 ")
    private Integer isLocked;

    @ApiModelProperty(value = "禁用原因")
    private String lockCase;

    @ApiModelProperty(value = "禁用时间")
    private Date lockedTime;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "职业")
    private String profession;

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

    @ApiModelProperty(value = "国家id")
    private Long countryId;

    @ApiModelProperty(value = "国家名")
    private String country;

    @ApiModelProperty(value = "省份")
    private Long provinceId;

    @ApiModelProperty(value = "省份名称")
    private String province;

    @ApiModelProperty(value = "城市")
    private Long cityId;

    @ApiModelProperty(value = "城市名")
    private String city;

    @ApiModelProperty(value = "区/镇")
    private Long districtId;

    @ApiModelProperty(value = "区/镇名")
    private String district;

    @ApiModelProperty(value = "详细地区")
    private String address;

    @ApiModelProperty(value = "个性签名")
    private String slogan;

}