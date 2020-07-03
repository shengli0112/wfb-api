/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.security.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberEntity;

/**
 * 用户
 *
 * @author Tim tim@ruitukeji.com
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static MemberDataEntity getUser() {
        Subject subject = getSubject();
        if (subject == null) {
            return new MemberDataEntity();
        }

        MemberDataEntity user = (MemberDataEntity)subject.getPrincipal();
        if (user == null) {
            return new MemberDataEntity();
        }

        return user;
    }

    /**
     * 获取用户信息
     */
    public static MemberEntity getWdpUser() {
        Subject subject = getSubject();
        if (subject == null) {
            return new MemberEntity();
        }

        MemberEntity user = (MemberEntity)subject.getPrincipal();
        if (user == null) {
            return new MemberEntity();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

}