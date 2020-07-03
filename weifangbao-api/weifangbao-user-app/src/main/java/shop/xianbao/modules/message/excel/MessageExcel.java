package shop.xianbao.modules.message.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Data
public class MessageExcel {
    @Excel(name = "")
    private Long id;

    @Excel(name = "消息类型 1平台消息  2业务消息")
    private Integer type;

    @Excel(name = "消息标题")
    private String title;

    @Excel(name = "消息内容")
    private String content;

    @Excel(name = "图片")
    private String image;

    @Excel(name = "消息链接")
    private String url;

    @Excel(name = "业务id")
    private Long targetId;

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