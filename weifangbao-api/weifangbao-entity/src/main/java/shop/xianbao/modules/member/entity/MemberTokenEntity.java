/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 用户Token
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@TableName("wxx_member_token")
public
class MemberTokenEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    private Long mid;
    
    /**
     * 终端类型  1=web 2=mobile 3=pad  4=watch
     */
    private Integer clientType;
    
    /**
     * 用户token
     */
    private String token;
    
    /**
     * 过期时间
     */
    private Date expireDate;

    /**
     * 最后生成token的ip地址
     */
    private String lastIp;

    /**
     * 最后登录设备终端id
     */
    private String terminalId;
}