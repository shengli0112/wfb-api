package shop.xianbao.modules.advertising.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.math.BigDecimal;

/**
 * 广告位表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("shc_adv_position")
public class AdvPositionEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告位置名
     */
    private String name;
    /**
     * 广告位简介
     */
    private String intro;
    /**
     * 广告展示方式：0幻灯片多广告展示,1单广告展示
     */
    private Integer displayMode;
    /**
     * 广告位高度
     */
    private Integer height;
    /**
     * 广告位宽度
     */
    private Integer width;
    /**
     * 广告位单价
     */
    private BigDecimal price;
    /**
     * 广告位点击率
     */
    private Integer clickNum;
    /**
     * 默认占位图
     */
    private String defaultSpaceImage;
    /**
     * 
     */
    private Integer displayTerminal;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}