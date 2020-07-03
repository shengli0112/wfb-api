/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.enums;

/**
 * 用户状态
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public enum UserStatusEnum {
    /**
     * 冻结
     */
    DISABLE(0),
    /**
     * 正常
     */
    ENABLED(1),
    /**
     * 黑名单
     */
    BLACKLIST(-1);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
