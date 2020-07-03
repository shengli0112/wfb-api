package shop.xianbao.modules.appversion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

/**
 * 新增/详情/修改接口的DTO
 *
 * @author will
 * @since 1.0.0 2019-03-22
 */
@Data
@ApiModel(value = "")
public class SysAppVersionDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "APP类型 1=用户Android端 2=商家Android端")
    private Integer appType;

    @ApiModelProperty(value = "APP版本序号递增（用于比较）")
    private Integer versionSn;

    @ApiModelProperty(value = "APP版本号")
    private String versionNumber;

    @ApiModelProperty(value = "版本信息")
    private String versionInfo;

    @ApiModelProperty(value = "下载url")
    private String url;

    @ApiModelProperty(value = "是否强制更新  0否 1是")
    private Integer isForceUpdate;

}