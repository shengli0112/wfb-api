package shop.xianbao.modules.customer.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@Data
public class CustomerExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "用户id")
    private Long unionId;

    @Excel(name = "姓")
    private String surname;

    @Excel(name = "名")
    private String name;

    @Excel(name = "姓名首拼")
    private String firstSpelling;

    @Excel(name = "电话号码")
    private String mobile;

    @Excel(name = "性别 0保密 1男 2女")
    private Integer gender;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "是否星标 0否 1是")
    private Integer isMarked;

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