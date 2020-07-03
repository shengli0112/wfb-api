package shop.xianbao.modules.appversion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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

    @ApiModelProperty(value = "ID")
    @Null(message = "{id.null}", groups = {AddGroup.class})
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
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

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}