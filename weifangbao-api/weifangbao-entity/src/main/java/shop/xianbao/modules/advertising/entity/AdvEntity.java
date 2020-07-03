package shop.xianbao.modules.advertising.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("shc_adv")
public class AdvEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告位id
     */
    private Long apId;
    /**
     * 广告内容描述
     */
    private String title;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 广告内容图片URL
     */
    private String imageUrl;
    /**
     * 广告点击率
     */
    private Integer clickNum;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}