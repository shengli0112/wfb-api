package shop.xianbao.modules.fastNavigation.dto;

import lombok.Data;
import shop.xianbao.common.utils.EnumUtil;
import shop.xianbao.modules.fastNavigation.enums.NavigationLayoutTypeEnum;

import java.util.List;

/**
 * @program: IndexFastNavigationDTO
 * @description:
 * @author: yh
 * @create: 2019-05-30 16:44
 **/
@Data
public class IndexFastNavigationDTO {
    private Integer layoutType;

    private String layoutTypeName;

    private List<FastNavigationListDTO> navigationList;

    public String getLayoutTypeName() {
        return EnumUtil.getEnumBycode(NavigationLayoutTypeEnum.class, layoutType).getName();
    }

}
