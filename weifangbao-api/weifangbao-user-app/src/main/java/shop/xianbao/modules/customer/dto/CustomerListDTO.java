package shop.xianbao.modules.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-12
 */
@Data
@ApiModel(value = "客户表-列表")
public class CustomerListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "姓")
    private String surname;

    @ApiModelProperty(value = "名")
    private String name;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "姓名首拼")
    private String firstSpelling;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "性别 0保密 1男 2女")
    private Integer gender;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否星标 0否 1是")
    private Integer isMarked;

}