package shop.xianbao.modules.member.entity;

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
@EqualsAndHashCode(callSuper = false)
@TableName("wxx_member")
public
class MemberEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * id 对应  union_user.id
     */
    private Long    unionId;
    /**
     * 是否可用  0：正常  1：停用
     */
    private Integer isLocked;
    /**
     * 禁用原因
     */
    private String  lockCase;
    /**
     * 禁用时间
     */
    private Date    lockedTime;
    
    public
    MemberEntity(){
        this.setIsLocked(0);
        this.setIsDeleted(0);
    }
    
    public
    MemberEntity(Long unionId){
        this();
        this.setUnionId(unionId);
    }
}