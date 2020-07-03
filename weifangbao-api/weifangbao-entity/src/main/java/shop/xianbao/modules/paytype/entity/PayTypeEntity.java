package shop.xianbao.modules.paytype.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 支付方式
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_pay_type")
public class PayTypeEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式code
     */
    private String payCode;

    /**
     * 支付方式名称
     */
    private String payName;

    /**
     * beanid
     */
    private String strategyBeanId;

    /**
     * 调用方式
     */
    private String tradeType;

    /**
     * 配置参数
     */
    private String config;

    /**
     * 是否需要实名认证
     */
    private Integer isRealName;

    private Integer status;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}