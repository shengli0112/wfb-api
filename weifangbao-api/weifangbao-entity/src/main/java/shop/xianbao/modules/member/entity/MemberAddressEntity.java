package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.math.BigDecimal;

/**
 * 用户收货地址表
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_member_address")
public class MemberAddressEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一用户ID union_user.id
     */
    private Long unionId;
    /**
     * member.id
     */
    private Long memberId;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 国家
     */
    private Long country;
    /**
     * 省份
     */
    private Long province;
    /**
     * 城市
     */
    private Long city;
    /**
     * 地区
     */
    private Long district;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zipcode;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 默认收货地址 0否 1是
     */
    private Integer isDefault;
    /**
     * 地址经度
     */
    private BigDecimal longitude;
    /**
     * 地址纬度
     */
    private BigDecimal latitude;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}