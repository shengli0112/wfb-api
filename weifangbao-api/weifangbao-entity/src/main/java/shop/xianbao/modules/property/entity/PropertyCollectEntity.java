package shop.xianbao.modules.property.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import shop.xianbao.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 楼盘收藏表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_property_collect")
public class PropertyCollectEntity extends BaseEntity {
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