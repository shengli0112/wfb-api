package shop.xianbao.modules.appversion.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 
 *
 * @author will
 * @since 1.0.0 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yt_sys_app_version")
public class SysAppVersionEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * APP类型 1=用户Android端 2=商家Android端
     */
    private Integer appType;
    /**
     * APP版本序号  递增（用于比较）
     */
    private Integer versionSn;

    /**
     * APP版本号 显示版本号
     */
    private String versionNumber;
    /**
     * 版本信息
     */
    private String versionInfo;
    /**
     * 下载url
     */
    private String url;
    /**
     * 是否强制更新  0否 1是
     */
    private Integer isForceUpdate;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;

    /**
     * 排序
     */
    private Integer sort;
}