/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.security.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String realName;

    private String headUrl;

    private Integer gender;

    private String email;

    private String mobile;

    private Long deptId;

    private String password;

    private Integer status;

    private Integer superAdmin;

    /**
     * 部门数据权限
     */
    private List<Long> deptIdList;

}