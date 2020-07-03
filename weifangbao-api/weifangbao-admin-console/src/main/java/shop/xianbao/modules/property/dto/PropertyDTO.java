package shop.xianbao.modules.property.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.validator.group.UpdateGroup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Data
@ApiModel(value = "楼盘表")
public class PropertyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "unionId")
    private String unionId;

    @ApiModelProperty(value = "父级区域id")
    private Long pAreaId;

    @ApiModelProperty(value = "父级区域名称")
    private String pAreaName;

    @ApiModelProperty(value = "所在区域")
    private Long areaId;

    @ApiModelProperty(value = "所在区域名称")
    private String areaName;

    @ApiModelProperty(value = "楼盘名称")
    private String propertyName;

    @ApiModelProperty(value = "楼盘别名")
    private String propertyAliasName;

    @ApiModelProperty(value = "楼盘简介")
    private String propertyIntroduce;

    @ApiModelProperty(value = "楼盘地址")
    private String propertyAddress;

    @ApiModelProperty(value = "经度")
    private Double addressLon;

    @ApiModelProperty(value = "纬度")
    private Double addressLat;

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

    @ApiModelProperty(value = "建筑结构")
    private String buildingStructure;

    @ApiModelProperty(value = "开盘时间")
    private Date openingDate;

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

    @ApiModelProperty(value = "物业类型")
    private String propertyType;

    @ApiModelProperty(value = "装修标准")
    private String decorationStandard;

    @ApiModelProperty(value = "产权年限")
    private String propertyRightYears;

    @ApiModelProperty(value = "开发商")
    private String developer;

    @ApiModelProperty(value = "售卖楼栋")
    private String saleBuilding;

    @ApiModelProperty(value = "最新开盘")
    private Date latestOpening;

    @ApiModelProperty(value = "最新交房")
    private Date latestHand;

    @ApiModelProperty(value = "占地面积")
    private String coverArea;

    @ApiModelProperty(value = "建筑面积")
    private String buildingArea;

    @ApiModelProperty(value = "容积率")
    private String plotRatio;

    @ApiModelProperty(value = "绿化率")
    private String afforestationRatio;

    @ApiModelProperty(value = "规划车位")
    private String planPark;

    @ApiModelProperty(value = "车位配比")
    private String parkRatio;

    @ApiModelProperty(value = "规划楼栋")
    private String planBuilding;

    @ApiModelProperty(value = "规划户数")
    private String planHouseholds;

    @ApiModelProperty(value = "物业公司")
    private String propertyCompany;

    @ApiModelProperty(value = "物业费用")
    private BigDecimal propertyCost;

    @ApiModelProperty(value = "供暖方式")
    private String heatingMode;

    @ApiModelProperty(value = "供水")
    private String waterSupply;

    @ApiModelProperty(value = "供电")
    private String powerSupply;

    @ApiModelProperty(value = "预售许可证")
    private String preSalePermit;

    @Range(min = 1, max = 5, groups = UpdateGroup.class, message = "propertyStatus值非法")
    @ApiModelProperty(value = "楼盘状态  0 无  1待审核  2审核通过  3驳回  4上架  5下架")
    private Integer propertyStatus;

    @Range(min = 0, max = 1, groups = UpdateGroup.class, message = "isTop值非法")
    @ApiModelProperty(value = "是否置顶 0否 1是")
    private Integer isTop;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "是否新楼盘")
    private Integer isNew;

    @ApiModelProperty(value = "是否折扣楼盘")
    private Integer isDiscount;

    @ApiModelProperty(value = "是否网红楼盘")
    private Integer isPop;

}