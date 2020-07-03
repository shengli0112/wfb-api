package shop.xianbao.modules.navigation.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "导航管理-模块管理")
public class NavigationModuleTypeDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "模块名称")
    private String name;
}
