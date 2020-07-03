package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 会员登录日志
 *
 * @author wdp
 * @since 1.0.0 2019-01-09
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_log_login")
public class MemberLogLoginEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 用户操作   0：用户登录   1：用户退出
     */
    private Integer operation;
    /**
     * 状态  0：失败    1：成功    2：账号已锁定
     */
    private Integer status;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 操作IP
     */
    private String ip;
    /**
     * 用户名
     */
    private String creatorName;
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