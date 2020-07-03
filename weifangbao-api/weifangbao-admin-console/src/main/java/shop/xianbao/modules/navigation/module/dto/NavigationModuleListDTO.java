package shop.xianbao.modules.navigation.module.dto;

import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 导航管理-模块管理
 * 列表DTO
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
public class NavigationModuleListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    private Long id;

    private String name;

    private String url;

    private Integer sort;

    private Integer type;

    private Long creator;

    private Date createDate;

    private Long updater;

    private Date updateDate;

}