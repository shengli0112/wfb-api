package shop.xianbao.modules.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * app首页菜单表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
@Data
@ApiModel(value = "app首页菜单表-列表")
public class AppMenuListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单URL 为null时不跳转")
    private String menuUrl;

    @ApiModelProperty(value = "状态  0停用 1启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}