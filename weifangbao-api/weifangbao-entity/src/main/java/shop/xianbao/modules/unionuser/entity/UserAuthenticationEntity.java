package shop.xianbao.modules.unionuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_user_authentication")
public class UserAuthenticationEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * union_id
     */
    private Long unionId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 门店地址
     */
    private String companyAddress;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 门头照
     */
    private String companyPhoto;

    /**
     * 审核状态 0申请中 1待审核 2审核通过 3审核驳回
     */
    private Integer status;

    /**
     * 审核通过时间
     */
    private Date examineTime;

    /**
     * 审核备注
     */
    private String remark;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}