package shop.xianbao.modules.elink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 易联云打印机
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_elink_printer")
public class ElinkPrinterEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 打印机名称（自定义）
     */
    private String printName;
    /**
     * 易联云打印机终端号

     */
    private String machineCode;
    /**
     * 易联云终端密钥
     */
    private String msign;
    /**
     * 是否默认 0否 1是
     */
    private Integer isDefault;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}