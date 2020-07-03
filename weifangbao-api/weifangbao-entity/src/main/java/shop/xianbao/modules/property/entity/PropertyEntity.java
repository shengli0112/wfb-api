package shop.xianbao.modules.property.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yh_property")
public class PropertyEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * unionId
     */
    private String unionId;

    /**
     * 所在区域
     */
    private Long areaId;

    /**
     * 楼盘名称
     */
    private String propertyName;

    /**
     * 楼盘别名
     */
    private String propertyAliasName;

    /**
     * 楼盘简介
     */
    private String propertyIntroduce;

    /**
     * 楼盘地址
     */
    private String propertyAddress;

    /**
     * 经度
     */
    private Double addressLon;

    /**
     * 纬度
     */
    private Double addressLat;

    /**
     * 楼盘特色
     */
    private String propertyCharacteristics;

    /**
     * 最小建面
     */
    private String minBuildArea;

    /**
     * 最大建面
     */
    private String maxBuildArea;

    /**
     * 参考均价 元
     */
    private BigDecimal unitPrice;

    /**
     * 参考总价 万元
     */
    private BigDecimal totalPrice;

    /**
     * 佣金 元
     */
    private String commission;

    /**
     * 建筑结构
     */
    private String buildingStructure;

    /**
     * 开盘时间
     */
    private Date openingDate;

    /**
     * 售卖状态
     */
    private String saleStatus;

    /**
     * 售卖户型
     */
    private String saleApartment;

    /**
     * 售楼地址
     */
    private String saleAddress;

    /**
     * 效果图
     */
    private String designPictures;

    /**
     * 户型图
     */
    private String apartmentPictures;

    /**
     * 标签
     */
    private String labels;

    /**
     * 物业类型
     */
    private String propertyType;

    /**
     * 装修标准
     */
    private String decorationStandard;

    /**
     * 产权年限
     */
    private String propertyRightYears;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 售卖楼栋
     */
    private String saleBuilding;

    /**
     * 最新开盘
     */
    private Date latestOpening;

    /**
     * 最新交房
     */
    private Date latestHand;

    /**
     * 占地面积
     */
    private String coverArea;

    /**
     * 建筑面积
     */
    private String buildingArea;

    /**
     * 容积率
     */
    private String plotRatio;

    /**
     * 绿化率
     */
    private String afforestationRatio;

    /**
     * 规划车位
     */
    private String planPark;

    /**
     * 车位配比
     */
    private String parkRatio;

    /**
     * 规划楼栋
     */
    private String planBuilding;

    /**
     * 规划户数
     */
    private String planHouseholds;

    /**
     * 物业公司
     */
    private String propertyCompany;

    /**
     * 物业费用
     */
    private BigDecimal propertyCost;

    /**
     * 供暖方式
     */
    private String heatingMode;

    /**
     * 供水
     */
    private String waterSupply;

    /**
     * 供电
     */
    private String powerSupply;

    /**
     * 预售许可证
     */
    private String preSalePermit;

    /**
     * 楼盘状态  0 无  1待审核  2审核通过  3驳回  4上架  5下架
     */
    private Integer propertyStatus;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序字段 小值在前
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新者id
     */
    private Long updater;

    /**
     * 删除标识位：0正常 1删除
     */
    private Integer isDeleted;

    @ApiModelProperty(value = "是否新楼盘")
    private Integer isNew;

    @ApiModelProperty(value = "是否折扣楼盘")
    private Integer isDiscount;

    @ApiModelProperty(value = "是否网红楼盘")
    private Integer isPop;

    public
    void setUnionId(Long unionId){
        this.unionId = "" + unionId;
    }
    public
    void setUnionId(String unionId){
        this.unionId = unionId;
    }
}