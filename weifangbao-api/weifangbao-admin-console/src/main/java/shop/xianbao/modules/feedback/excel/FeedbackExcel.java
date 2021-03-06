package shop.xianbao.modules.feedback.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 意见反馈表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Data
public class FeedbackExcel {
    @Excel(name = "")
    private Long id;

    @Excel(name = "描述")
    private String description;

    @Excel(name = "联系方式")
    private String contactInfo;

    @Excel(name = "截图")
    private String pictures;

    @Excel(name = "上报者id")
    private Long reporterId;

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