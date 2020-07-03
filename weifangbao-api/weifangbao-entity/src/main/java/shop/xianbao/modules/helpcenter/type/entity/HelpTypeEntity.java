package shop.xianbao.modules.helpcenter.type.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 帮助类型管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wly_help_type")
public class HelpTypeEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 类别名称
     */
    @TableField(value="`name`")
    private String name;
    /**
     * 排序
     */
    private Integer sort;
}