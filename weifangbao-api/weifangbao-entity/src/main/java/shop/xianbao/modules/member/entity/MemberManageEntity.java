package shop.xianbao.modules.member.entity;

import lombok.Data;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 会员信息表
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Data
public
class MemberManageEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * id 对应  union_user.id
     */
    private Long unionId;

    private String email; //邮箱

    private String realname; //用户名

    private String nickname; //昵称

    private String avatar; //头像

    private String gender; //性别   0=未知  1=男   2=女  3=保密

    private Date birthday; //生日

    private String profession; //职业

    private String mobile;//手机

    /**
     * 是否可用  0：正常  1：停用
     */
    private Integer isLocked;

    //注册时间
    private Date createDate;

    //上次登录时间
    private Date lastLoginTime;

    //信用分
    private Integer creditScore;

    //我的推广员编号
    private String promoterNum;

    //父级推广员编号
    private String masPromoterNum;

    //积分
    private String integral;

    //账户金额
    private Integer amount;

    //会员等级
    private String memLevel;
}