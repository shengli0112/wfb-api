package shop.xianbao.modules.property.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Data
public class PropertyExcel {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "所在区域")
    private Long areaId;

    @Excel(name = "楼盘名称")
    private String propertyName;

    @Excel(name = "楼盘别名")
    private String propertyAliasName;

    @Excel(name = "楼盘简介")
    private String propertyIntroduce;

    @Excel(name = "楼盘地址")
    private String propertyAddress;

    @Excel(name = "楼盘特色")
    private String propertyCharacteristics;

    @Excel(name = "最小建面")
    private String minBuildArea;

    @Excel(name = "最大建面")
    private String maxBuildArea;

    @Excel(name = "参考均价 元")
    private BigDecimal unitPrice;

    @Excel(name = "参考总价 万元")
    private BigDecimal totalPrice;

    @Excel(name = "佣金 元")
    private String commission;

    @Excel(name = "建筑结构")
    private String buildingStructure;

    @Excel(name = "开盘时间")
    private Date openingDate;

    @Excel(name = "售卖状态")
    private String saleStatus;

    @Excel(name = "售卖户型")
    private String saleApartment;

    @Excel(name = "售楼地址")
    private String saleAddress;

    @Excel(name = "户型图")
    private String apartmentPictures;

    @Excel(name = "标签")
    private String labels;

    @Excel(name = "物业类型")
    private String propertyType;

    @Excel(name = "装修标准")
    private String decorationStandard;

    @Excel(name = "产权年限")
    private String propertyRightYears;

    @Excel(name = "开发商")
    private String developer;

    @Excel(name = "售卖楼栋")
    private String saleBuilding;

    @Excel(name = "最新开盘")
    private Date latestOpening;

    @Excel(name = "最新交房")
    private Date latestHand;

    @Excel(name = "占地面积")
    private String coverArea;

    @Excel(name = "建筑面积")
    private String buildingArea;

    @Excel(name = "容积率")
    private String plotRatio;

    @Excel(name = "绿化率")
    private String afforestationRatio;

    @Excel(name = "规划车位")
    private String planPark;

    @Excel(name = "车位配比")
    private String parkRatio;

    @Excel(name = "规划楼栋")
    private String planBuilding;

    @Excel(name = "规划户数")
    private String planHouseholds;

    @Excel(name = "物业公司")
    private String propertyCompany;

    @Excel(name = "物业费用")
    private BigDecimal propertyCost;

    @Excel(name = "供暖方式")
    private String heatingMode;

    @Excel(name = "供水")
    private String waterSupply;

    @Excel(name = "供电")
    private String powerSupply;

    @Excel(name = "预售许可证")
    private String preSalePermit;

    @Excel(name = "楼盘状态  0 无  1待审核  2审核通过  3驳回  4上架  5下架")
    private Integer propertyStatus;

    @Excel(name = "备注")
    private String remark;

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