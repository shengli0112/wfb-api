package shop.xianbao.modules.message.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@Data
public class MessageRecordExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "发送人id")
    private Long senderId;

    @Excel(name = "接收人id")
    private Long receiverId;

    @Excel(name = "发送内容")
    private String content;

    @Excel(name = "发送时间")
    private Date sendTime;

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