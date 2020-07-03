package shop.xianbao.modules.property.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
public class HelpFindHouseExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "unionId")
    private Long unionId;

    @Excel(name = "购房区域")
    private Long areaId;

    @Excel(name = "购房预算")
    private BigDecimal budget;

    @Excel(name = "户型")
    private String apartment;

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