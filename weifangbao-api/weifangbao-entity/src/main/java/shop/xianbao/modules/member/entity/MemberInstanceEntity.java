package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 会员实例信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_instance")
public class MemberInstanceEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 点亮的用户类型统计数
     */
    private Integer count;
    /**
     * 0:未开通 1:开通
     */
    private Integer buyer;
    /**
     * 0:未开通 1:开通
     */
    private Integer seller;
    /**
     * 0:未开通 1:开通
     */
    private Integer distribution;
    /**
     * 更新者ID
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