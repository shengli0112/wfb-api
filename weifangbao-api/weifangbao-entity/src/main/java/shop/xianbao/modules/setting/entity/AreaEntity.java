package shop.xianbao.modules.setting.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 地区表/行政划分表
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wxx_common_area")
public class AreaEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField(value="`name`")
    private String name;
    /**
     * 归属ID
     */
    private Long pid;
    /**
     * 等级类型，1-省；2-市; 3-县、区;  4:镇; 5:乡村
     */
    private Integer level;
    /**
     * 区号
     */
    private String cityCode;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 城市拼音
     */
    private String pinyin;
    /**
     * 详细名称
     */
    private String mergerName;
    /**
     * 简短名称
     */
    private String shortName;
    /**
     * 经度（百度地图）
     */
    private String lng;
    /**
     * 纬度（百度地图）
     */
    private String lat;
    /**
     * 状态 暂未使用
     */
    private Integer status;
    /**
     * 是否为热门城市 0=不是 1=是 暂未使用
     */
    private Integer isHot;
    /**
     * 排序字段 小值在前
     */
    private Integer sort;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    /**
     * 更新者id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}