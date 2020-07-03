package shop.xianbao.modules.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@Data
@ApiModel(value = "客户表")
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = {UpdateGroup.class})
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long unionId;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @NotEmpty(groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "姓")
    private String surname;

    @ApiModelProperty(value = "名")
    private String name;

    @ApiModelProperty(value = "姓名首拼")
    private String firstSpelling;

    @NotNull(groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @NotNull(groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer gender;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否星标 0否 1是")
    private Integer isMarked;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "更新者id")
    private Long updater;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}