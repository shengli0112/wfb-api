package shop.xianbao.modules.report.enums;

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
public enum ReportStatusEnum {
    UN_KNOWN(0, ""),
    REPORT(1, "已报备"),
    INSPECTION(2, "已看房"),
    SUBSCRIPTION(3, "已认购"),
    SIGNING(4, "已签约"),
    INVALID(5, "已失效"),
    ;

    private Integer value;

    private String name;


}
