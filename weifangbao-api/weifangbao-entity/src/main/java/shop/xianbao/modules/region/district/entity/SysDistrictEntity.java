package shop.xianbao.modules.region.district.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 五级地区(区或县)管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wly_sys_district")
public class SysDistrictEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 区(县)名
     */
    private String districtName;
    /**
     * 二级地区(市)ID
     */
    private Long cityId;
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
}