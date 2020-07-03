/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.member.enums;

/**
 * 账户开通枚举
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public enum AccountOpenStatusEnum {
    /**
     * 开通
     */
    OPEN(1),
    /**
     * 未开通
     */
    NO_OPEN(0);

    private int value;

    AccountOpenStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
