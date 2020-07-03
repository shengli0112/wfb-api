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
public enum CorrectionStatusEnum {
    UN_HANDLE(0, "未处理"),
    HANDLE(1, "已处理"),
    ;

    private Integer value;

    private String name;


}
