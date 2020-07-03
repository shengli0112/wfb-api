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
 * 会员等级
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_level")
public class MemberLevelEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    private Integer    storeId;
    /**
     * 等级名称
     */
    private String     levelName;
    /**
     * 累计积分
     */
    private Integer    minIntegral;
    /**
     * 折扣率
     */
    private BigDecimal goodsDiscount;
    /**
     * 等级描述
     */
    private String     description;
    /**
     * 是否是默认
     */
    private Integer    isDefault;
    /**
     * 消费额度
     */
    private BigDecimal quota;
    /**
     * 升级条件  1.累计积分 2.消费额度 3.同时满足
     */
    private Integer    upgrade;
    /**
     * 等级排序
     */
    private Integer    sort;
    /**
     * 1.或 2. 且
     */
    private Integer    relation;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
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