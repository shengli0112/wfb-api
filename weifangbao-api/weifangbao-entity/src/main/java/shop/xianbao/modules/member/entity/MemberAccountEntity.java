package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员账目统计表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_account")
public class MemberAccountEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 会员uid
     */
    private Long       uid;
    /**
     * 店铺ID
     */
    private Integer    storeId;
    /**
     * 会员积分
     */
    private Integer    point;
    /**
     * 余额
     */
    private String     balance;
    /**
     * 购物币
     */
    private Integer    coin;
    /**
     * 会员消费
     */
    private BigDecimal memberCunsum;
    /**
     * 会员累计积分
     */
    private Integer    memberSumPoint;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long       updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date       updateDate;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer    isDeleted;
}