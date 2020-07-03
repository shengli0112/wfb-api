/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.enums;

/**
 * 账户类型枚举
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public enum AccountTypeEnum {
    /**
     * 积分
     */
    POINT(1),
    /**
     * 余额
     */
    BALANCE(2),
    /**
     * 购物币
     */
    COIN(3);

    private int value;

    AccountTypeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
