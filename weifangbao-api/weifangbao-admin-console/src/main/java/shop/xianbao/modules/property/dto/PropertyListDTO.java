package shop.xianbao.modules.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.modules.property.enums.PropertyStatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 楼盘表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-11-13
 */
@Data
@ApiModel(value = "楼盘表-列表")
public class PropertyListDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private Long unionId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "所在区域")
    private Long areaId;

    @ApiModelProperty(value = "所在区域名称")
    private String areaName;

    @ApiModelProperty(value = "楼盘名称")
    private String propertyName;

    @ApiModelProperty(value = "楼盘地址")
    private String propertyAddress;

    @ApiModelProperty(value = "楼盘特色")
    private String propertyCharacteristics;

    @ApiModelProperty(value = "最小建面")
    private String minBuildArea;

    @ApiModelProperty(value = "最大建面")
    private String maxBuildArea;

    @ApiModelProperty(value = "参考均价 元")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "参考总价 万元")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "佣金 元")
    private String commission;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开盘时间")
    private Date openingDate;

    @ApiModelProperty(value = "建筑结构")
    private String buildingStructure;

    @ApiModelProperty(value = "售卖状态")
    private String saleStatus;

    @ApiModelProperty(value = "售卖户型")
    private String saleApartment;

    @ApiModelProperty(value = "售楼地址")
    private String saleAddress;

    @ApiModelProperty(value = "效果图")
    private String designPictures;

    @ApiModelProperty(value = "户型图")
    private String apartmentPictures;

    @ApiModelProperty(value = "标签")
    private String labels;

    @ApiModelProperty(value = "楼盘状态  0 无  1待审核  2审核通过  3驳回  4上架  5下架")
    private Integer propertyStatus;

    @ApiModelProperty(value = "是否置顶 0否 1是")
    private Integer isTop;

    @ApiModelProperty(value = "楼盘状态  0 无  1待审核  2审核通过  3驳回  4上架  5下架")
    private String propertyStatusName;

    public String getPropertyStatusName() {
        return Objects.requireNonNull(EnumUtil.getEnumBycode(PropertyStatusEnum.class, propertyStatus)).getName();
    }

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    private Integer sort;

    @ApiModelProperty(value = "是否新楼盘")
    private Integer isNew;

    @ApiModelProperty(value = "是否折扣楼盘")
    private Integer isDiscount;

    @ApiModelProperty(value = "是否网红楼盘")
    private Integer isPop;
}