package shop.xianbao.modules.fastNavigation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 导航管理-导航内容
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("shc_fast_navigation")
public class FastNavigationEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 导航名称
     */
    private String name;
    /**
     * 导航图标URL
     */
    private String icon;
    /**
     * 终端类型 0-PC端,1-手机端
     */
    private Integer type;
    /**
     * 页面链接
     */
    private String pageUrl;
    /**
     * 所属导航模块ID
     */
    private Long positionId;

    /**
     * 布局类型 1: 1*4  2:3*4  3:2*5  4:3*5
     */
    private Integer layoutType;
    /**
     * 是否显示 0-否,1-是
     */
    private Integer showFlag;
}