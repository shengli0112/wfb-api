package shop.xianbao.modules.navigation.content.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 导航管理-导航内容
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wly_sys_navigation_content")
public class NavigationContentEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 导航名称
     */
    @TableField(value="`name`")
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 导航类型 0-PC端,1-手机端
     */
    private Integer type;
    /**
     * 所属导航模块ID
     */
    private Long moduleId;
    /**
     * 自定义链接
     */
    private String customUrl;
    /**
     * 是否新窗口打开 0-否,1-是
     */
    private Integer openInNewWindowFlag;
    /**
     * 是否显示 0-否,1-是
     */
    private Integer showFlag;
    /**
     * 导航图标URL
     */
    private String logoUrl;
    /**
     * 更新者ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

    /**
     * 所属导航模块的类型名称
     */
    @TableField(exist = false)
    private String modeleName;

    /**
     * 所属导航模块的链接地址
     */
    @TableField(exist = false)
    private String url;
    /**
     * 是否显示的值 0-否,1-是
     */
    @TableField(exist = false)
    private String showFlagValue;
}