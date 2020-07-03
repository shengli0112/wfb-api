package shop.xianbao.modules.member.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import java.util.Date;

/**
 * 会员信息表
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberDataEntity extends UnionUserEntity {
    private static final long serialVersionUID = 1L;

    private Long unionId;

    /**
     * id
     */
    private Long mid;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 是否已认证
     */
    private Integer isRealAuth;

    /**
     * 排序字段 小值在前
     */
    private Integer msort;

    /**
     * 创建者
     */
    private Long mcreator;

    /**
     * 创建时间
     */
    private Date mcreateDate;

    /**
     * 更新者
     */
    private Long mupdater;

    /**
     * 更新时间
     */
    private Date mupdateDate;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer misDeleted;

    /**
     * 是否可用  0：正常  1：停用
     */
    private Integer misLocked;

    /**
     * 禁用原因
     */
    private String mlockCase;

    /**
     * 禁用时间
     */
    private Date mlockedTime;

    private MemberTokenEntity token;

}