/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.enums;

/**
 * 超级管理员枚举
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public enum SuperAdminEnum {
    YES(1), NO(0);

    private int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}