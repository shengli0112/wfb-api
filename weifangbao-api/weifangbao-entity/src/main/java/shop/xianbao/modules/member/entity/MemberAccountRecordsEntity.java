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
 * 会员流水账表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_account_records")
public class MemberAccountRecordsEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long       uid;
    /**
     * 店铺ID
     */
    private Integer    storeId;
    /**
     * 账户类型1.积分2.余额3.购物币
     */
    private Integer    accountType;
    /**
     * 正负号 1:正号 2:负号
     */
    private Integer    sign;
    /**
     * 数量
     */
    private BigDecimal number;
    /**
     * 产生方式1.商城订单2.订单退还3.兑换4.充值5.签到6.分享7.注册8.提现9提现退还
     */
    private Integer    fromType;
    /**
     * 相关表的数据ID
     */
    private Integer    dataId;
    /**
     * 数据相关内容描述文本
     */
    private String     text;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long       updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}