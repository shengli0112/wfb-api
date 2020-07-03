package shop.xianbao.modules.property.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.util.Date;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_property_region")
public class PropertyRegionEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long parentId;

    private String regionName;

    private Integer isShow;

    private Integer sort;

    private Date updateDate;

    private Long updater;

    private Integer isDeleted;

}