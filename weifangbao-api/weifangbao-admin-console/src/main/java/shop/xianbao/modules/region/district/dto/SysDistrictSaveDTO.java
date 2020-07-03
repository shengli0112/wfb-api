package shop.xianbao.modules.region.district.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 五级地区(区或县)管理
 * 新增DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Data
@ApiModel(value = "五级地区(区或县)管理")
public class SysDistrictSaveDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区(县)名")
    @NotBlank(message = "{common.name.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String districtName;

    @ApiModelProperty(value = "四级地区(市)ID")
    @NotNull(message = "{district.cityId.require}", groups = {AddGroup.class, UpdateGroup.class})
    private Long cityId;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "{common.sort.require}", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
}