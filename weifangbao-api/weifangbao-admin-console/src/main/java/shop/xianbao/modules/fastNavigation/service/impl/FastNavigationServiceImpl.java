package shop.xianbao.modules.fastNavigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.fastNavigation.dao.FastNavigationDao;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationDTO;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationListDTO;
import shop.xianbao.modules.fastNavigation.entity.FastNavigationEntity;
import shop.xianbao.modules.fastNavigation.service.FastNavigationService;

import java.util.Map;

/**
 * 导航管理-导航内容
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-28
 */
@Service
public class FastNavigationServiceImpl extends XianbaoCrudServiceImpl<FastNavigationDao, FastNavigationEntity, FastNavigationDTO, FastNavigationListDTO> implements FastNavigationService {

    @Override
    public QueryWrapper<FastNavigationEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<FastNavigationEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        //        wrapper.orderByDesc("create_date");
        return wrapper;
    }
}