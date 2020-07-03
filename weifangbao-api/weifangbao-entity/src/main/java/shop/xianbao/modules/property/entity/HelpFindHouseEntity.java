package shop.xianbao.modules.property.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_help_find_house")
public class HelpFindHouseEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * unionId
     */
    private Long unionId;

    /**
     * 购房区域
     */
    private Long areaId;

    /**
     * 购房预算
     */
    private BigDecimal budget;

    /**
     * 户型
     */
    private String apartment;

    /**
     * 状态 0 停用 1启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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