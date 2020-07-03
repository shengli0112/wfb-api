package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 会员信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wdp_sys_member_baseinfo")
public class MemberBaseinfoEntity extends XianBaoBaseEntity{
	private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户头像
     */
    private String userHeadimg;
    /**
     * 用户等级Id
     */
    private Long memberLevel;
    /**
     * 是否是系统后台用户 0 不是 1 是
     */
    private Integer isSystem;
    /**
     * 是否是前台会员
     */
    private Integer isMember;
    /**
     * 联系手机号
     */
    private String mobile;
    /**
     * 手机号是否绑定 0 未绑定 1 绑定 
     */
    private Integer userTelBind;
    /**
     * qq号
     */
    private String userQq;
    /**
     * qq互联id
     */
    private String qqOpenid;
    /**
     * qq账号相关信息
     */
    private String qqInfo;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 是否邮箱绑定
     */
    private Integer userEmailBind;
    /**
     * 微信用户openid
     */
    private String wxOpenid;
    /**
     * 微信用户是否关注
     */
    private Integer wxIsSub;
    /**
     * 微信用户信息
     */
    private String wxInfo;
    /**
     * 附加信息
     */
    private String otherInfo;
    /**
     * 登录次数
     */
    private Integer loginNum;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别 0保密 1男 2女
     */
    private Integer sex;
    /**
     * 住址
     */
    private String location;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 微信unionid
     */
    private String wxUnionid;
    /**
     * 模板id
     */
    private Integer qrcodeTemplateId;
    /**
     * 微信用户关注时间
     */
    private Integer wxSubTime;
    /**
     * 微信用户取消关注时间
     */
    private Integer wxNotsubTime;
    /**
     * 生日
     */
    private Integer birthday;
    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
}