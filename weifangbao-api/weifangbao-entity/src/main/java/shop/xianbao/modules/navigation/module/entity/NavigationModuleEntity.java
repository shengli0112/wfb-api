package shop.xianbao.modules.navigation.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 导航管理-模块管理
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wly_sys_navigation_module")
public class NavigationModuleEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 模块名称
     */
    @TableField(value="`name`")
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 导航模块类型 0-PC端,1-手机端
     */
    private Integer type;
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
}