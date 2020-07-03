/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.dto;

import lombok.Data;
import shop.xianbao.modules.member.entity.MemberDataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录表单
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
public class LoginRetDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别   0=未知  1=男   2=女  3=保密
     */
    private Integer gender;

    /**
     * 是否经过实名认证 0=否 1=是
     */
    private Integer isRealAuth;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 职业
     */
    private String profession;

    /**
     * 国家id
     */
    private Long countryId;

    /**
     * 国家名
     */
    private String country;

    /**
     * 省份
     */
    private Long provinceId;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 城市
     */
    private Long cityId;

    /**
     * 城市名
     */
    private String city;

    /**
     * 区/镇
     */
    private Long districtId;

    /**
     * 区/镇名
     */
    private String district;

    /**
     * 详细地区
     */
    private String address;

    private TokenRetDTO token;

    /**
     * 个性签名
     */
    private String slogan;

    //  以下敏感信息 不轻易暴露
    //    /**
    //     * 身份证号
    //     */
    //    private String idCardNo;
    //
    //    /**
    //     * 身份证正面照片
    //     */
    //    private String idCardFront;
    //
    //    /**
    //     * 身份证背面照片
    //     */
    //    private String idCardBack;
    //
    //    /**
    //     * 手持身份证照片
    //     */
    //    private String idCardHand;

    public LoginRetDTO() {
    }

    public LoginRetDTO(MemberDataEntity user) {
        this.setMobile(user.getMobile());
        this.setEmail(user.getEmail());
        this.setRealname(user.getRealname());
        this.setNickname(user.getNickname());
        this.setAvatar(user.getAvatar());
        this.setGender(user.getGender());
        this.setIsRealAuth(user.getIsRealAuth());
        this.setBirthday(user.getBirthday());
        this.setProfession(user.getProfession());
        this.setCountry(user.getCountry());
        this.setCountryId(user.getCountryId());
        this.setProvince(user.getProvince());
        this.setProvinceId(user.getProvinceId());
        this.setCity(user.getCity());
        this.setCityId(user.getCityId());
        this.setDistrict(user.getDistrict());
        this.setDistrictId(user.getDistrictId());
        this.setAddress(user.getAddress());
        this.setSlogan(user.getSlogan());
        this.setToken(new TokenRetDTO());
    }

}