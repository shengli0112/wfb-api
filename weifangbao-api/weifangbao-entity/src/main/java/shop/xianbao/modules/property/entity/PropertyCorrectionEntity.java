package shop.xianbao.modules.property.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_property_correction")
public class PropertyCorrectionEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 楼盘id
     */
    private Long propertyId;

    /**
     * 错误描述
     */
    private String description;

    /**
     * 联系方式
     */
    private String contactInfo;

    /**
     * 截图
     */
    private String screenshot;

    /**
     * 上报者id
     */
    private Long reporterId;

    /**
     * 处理状态 0未处理 1已处理
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
}