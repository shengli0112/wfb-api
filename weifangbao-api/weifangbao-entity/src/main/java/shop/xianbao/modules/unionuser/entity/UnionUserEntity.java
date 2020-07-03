package shop.xianbao.modules.unionuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础用户表（登录验证）
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wxx_union_user")
public class UnionUserEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名用于登录
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密盐值
     */
    private String salt;
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
     * 微信openId
     */
    private String openId;

    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 身份证正面照片
     */
    private String idCardFront;
    /**
     * 身份证背面照片
     */
    private String idCardBack;
    /**
     * 手持身份证照片
     */
    private String idCardHand;
    /**
     * 是否经过实名认证 0=否 1=是
     */
    private Integer isRealAuth;
    /**
     * 是否可用  0：正常  1：停用 
     */
    private Integer isLocked;
    /**
     * 禁用原因
     */
    private String lockCase;
    /**
     * 禁用时间
     */
    private Date lockedTime;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 职业
     */
    private String profession;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
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
    /**
     * 个性签名
     */
    private String slogan;

    public UnionUserEntity() {
    }

    public UnionUserEntity(String mobile) {
        this.mobile = mobile;
        this.username = mobile;
    }

    /**
     * 上一级id
     */
    private Long pId;
    /**
     * 上二级id
     */
    private Long gId;
    /**
     * 用户金币余额
     */
    private BigDecimal goldCount;
}