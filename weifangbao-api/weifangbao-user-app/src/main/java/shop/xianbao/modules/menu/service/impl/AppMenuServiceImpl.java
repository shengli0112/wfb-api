package shop.xianbao.modules.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.menu.dao.AppMenuDao;
import shop.xianbao.modules.menu.dto.AppMenuDTO;
import shop.xianbao.modules.menu.dto.AppMenuListDTO;
import shop.xianbao.modules.menu.entity.AppMenuEntity;
import shop.xianbao.modules.menu.service.AppMenuService;

import java.util.Map;

/**
 * app首页菜单表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-11
 */
@Service
public class AppMenuServiceImpl extends XianbaoCrudServiceImpl<AppMenuDao, AppMenuEntity, AppMenuDTO, AppMenuListDTO> implements AppMenuService {

    @Override
    public QueryWrapper<AppMenuEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppMenuEntity> wrapper = new QueryWrapper<>();
        Object menuPositionObj = params.get("menuPosition");
        if (menuPositionObj != null && StringUtils.isNotBlank(menuPositionObj.toString())) {
            wrapper.eq("menu_position", menuPositionObj.toString());
        }
        wrapper.eq("status", 1);
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}