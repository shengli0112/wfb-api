package shop.xianbao.modules.menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * app首页菜单表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_app_menu")
public class AppMenuEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单URL 为null时不跳转
     */
    private String menuUrl;
    /**
     * 状态  0停用 1启用
     */
    private Integer status;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}