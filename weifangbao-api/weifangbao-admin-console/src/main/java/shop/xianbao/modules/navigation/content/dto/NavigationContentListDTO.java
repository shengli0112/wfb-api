package shop.xianbao.modules.navigation.content.dto;

import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 导航管理-导航内容
 * 列表DTO
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Data
public class NavigationContentListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer sort;

    private Integer type;

    /**
     * 所属导航模块的类型名称
     */
    private String modeleName;

    /**
     * 所属导航模块的链接地址
     */
    private String url;

    private String customUrl;

    private Integer showFlag;

    private Date createDate;

    /**
     * 是否显示的值 0-否,1-是
     */
    private String showFlagValue;
}