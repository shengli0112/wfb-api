package shop.xianbao.modules.property.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: approveStatusEnum
 * @description: 状态枚举类
 * @author: yh
 * @create: 2019-08-07 14:04
 **/
@AllArgsConstructor
@Getter
public enum PropertyStatusEnum {
    NO_HANDLE(0, ""),
    UN_EXAMINE(1, "审核中"),
    PROVE(2, "通过"),
    UN_PROVE(3, "未通过"),
    ON_SALE(4, "上架"),
    UN_SALE(5, "下架"),
    ;

    private Integer value;

    private String name;


}
