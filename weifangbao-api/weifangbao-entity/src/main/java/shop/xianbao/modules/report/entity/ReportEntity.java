package shop.xianbao.modules.report.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_report")
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * unionId
     */
    private Long unionId;

    /**
     * 楼盘id
     */
    private Long propertyId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户性别
     */
    private Integer customerGender;

    /**
     * 客户手机号
     */
    private String mobile;

    /**
     * 是否保密
     */
    private Integer isSecret;

    /**
     * 看房时间
     */
    private Date appointTime;

    /**
     * 认购时间
     */
    private Date subscriptionTime;

    /**
     * 签约时间
     */
    private Date signingTime;

    /**
     * 失效时间
     */
    private Date invalidTime;

    /**
     * 报备状态 0 预留 1已报备 2已看房 3已认购 4已签约 5已失效
     */
    private Integer status;

    /**
     * 排序字段 小值在前
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新者id
     */
    private Long updater;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;

    public ReportEntity(
        Long unionId, Long propertyId, Long customerId, String customerName, Integer customerGender, String mobile, Integer isSecret, Date appointTime, Date invalidTime, Integer status) {
        this.unionId = unionId;
        this.propertyId = propertyId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.mobile = mobile;
        this.isSecret = isSecret;
        this.appointTime = appointTime;
        this.invalidTime = invalidTime;
        this.status = status;
    }
}