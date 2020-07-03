package shop.xianbao.modules.navigation.content.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否显示的枚举
 */
@AllArgsConstructor
@Getter
public enum ShowFlagEnum {

    YES(1, "是"), NO(0, "否");

    private Integer code;

    private String msg;
}
