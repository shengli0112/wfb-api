/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录表单
 *
 * @author Will
 */
@Data
public class InviteUserListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nickname;

    private String avatar;

    private Date createDate;

}