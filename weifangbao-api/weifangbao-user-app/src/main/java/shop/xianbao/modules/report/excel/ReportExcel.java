package shop.xianbao.modules.report.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@Data
public class ReportExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "unionId")
    private Long unionId;

    @Excel(name = "楼盘id")
    private Long propertyId;

    @Excel(name = "客户id")
    private Long customerId;

    @Excel(name = "看房时间")
    private Date appointTime;

    @Excel(name = "失效时间")
    private Date invalidTime;

    @Excel(name = "报备状态 0 预留 1已报备 2已看房 3已认购 4已签约 5已失效")
    private Integer status;

    @Excel(name = "排序字段 小值在前")
    private Integer sort;

    @Excel(name = "创建时间")
    private Date createDate;

    @Excel(name = "更新时间")
    private Date updateDate;

    @Excel(name = "创建者id")
    private Long creator;

    @Excel(name = "更新者id")
    private Long updater;

    @Excel(name = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}