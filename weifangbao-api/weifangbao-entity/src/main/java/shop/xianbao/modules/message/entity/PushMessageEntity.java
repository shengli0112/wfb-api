package shop.xianbao.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

/**
 * 消息推送表
 *
 * @author wxx
 * @since 1.0.0 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wxx_push_message")
public class PushMessageEntity extends XianBaoBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 业务用户类型  1=member 2=seller
     */
    private Integer userType;
    /**
     * 业务用户id
     */
    private Long userId;
    /**
     * 业务类型  1=新订单 2=接单超时 3=接单 4=配送 5=确认收货
     */
    private Integer bizType;
    /**
     * 业务id
     */
    private Long bizId;
    /**
     * 业务序号
     */
    private String bizSn;
    /**
     * 推送平台  all,app,ios,android,mobile all：全部平台 app: ios+android mobile: 手机
     */
    private String platform;
    /**
     * 推送目标 设备标识/手机号  如果要发广播（全部设备），则直接填写 “all”。
     */
    private String receiver;
    /**
     * 消息标题
     */
    private String msgTitle;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 附加字段
     */
    private String extras;
    /**
     * 业务参数
     */
    private String params;
    /**
     * 期望推送时间
     */
    private Integer pushTime;

    /**
     * 推送次数
     */
    private Integer pushTimes;
    /**
     * 是否已推送 0未发送 1已发送
     */
    private Integer isPushed;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
}