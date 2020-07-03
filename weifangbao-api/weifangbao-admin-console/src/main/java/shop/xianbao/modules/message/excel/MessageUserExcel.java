package shop.xianbao.modules.message.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Data
public class MessageUserExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "消息id")
    private Long messageId;

    @Excel(name = "用户id")
    private Long unionId;

    @Excel(name = "是否已读  0否 1是")
    private Integer isRead;

    @Excel(name = "阅读时间")
    private Date readTime;

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