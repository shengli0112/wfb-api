package shop.xianbao.modules.appversion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 列表DTO
 *
 * @author will
 * @since 1.0.0 2019-03-22
 */
@Data
@ApiModel(value = "-列表")
public class SysAppVersionListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "app版本表")
    private Long id;

    @ApiModelProperty(value = "APP类型 1=用户Android端 2=商家Android端")
    private Integer appType;

    @ApiModelProperty(value = "APP版本号")
    private String versionNumber;

    @ApiModelProperty(value = "版本信息")
    private String versionInfo;

    @ApiModelProperty(value = "下载url")
    private String url;

    @ApiModelProperty(value = "是否强制更新  0否 1是")
    private Integer isForceUpdate;

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

    @ApiModelProperty(value = "排序")
    private Integer sort;

}