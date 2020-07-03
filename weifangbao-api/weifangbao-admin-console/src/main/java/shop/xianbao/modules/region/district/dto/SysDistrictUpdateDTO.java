package shop.xianbao.modules.region.district.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * 五级地区(区或县)管理
 * 更新DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Data
@ApiModel(value = "五级地区(区或县)管理")
public class SysDistrictUpdateDTO extends SysDistrictSaveDTO {

    @ApiModelProperty(value = "五级地区(区或县)ID")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;
}
