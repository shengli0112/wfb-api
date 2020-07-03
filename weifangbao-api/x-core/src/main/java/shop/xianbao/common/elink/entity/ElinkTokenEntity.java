package shop.xianbao.common.elink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 易联云平台访问令牌表
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("yh_elink_token")
public class ElinkTokenEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 易联云应用id
     */
    private String clientId;
    /**
     * 访问令牌，API调用时需要，令牌可以重复使用无失效时间
     */
    private String accessToken;
    /**
     * 更新access_token所需，有效时间35天
     */
    private String refreshToken;
    /**
     * 令牌的有效时间，单位秒 (30天),注：该模式下可忽略此参数
     */
    private Integer expiresIn;
    /**
     * 权限
     */
    private String scope;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}