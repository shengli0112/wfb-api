package shop.xianbao.modules.helpcenter.content.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 帮助内容表
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wly_help_content")
public class HelpContentEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;
    /**
     * 帮助类别ID
     */
    private Long typeId;
    /**
     * 正文
     */
    private String content;
    /**
     * 是否显示,0-不显示，1-显示
     */
    private Integer showFlag;
    /**
     * 排序
     */
    private Integer sort;
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
     * 分类名称
     */
    @TableField(exist = false)
    private String typeName;
}