package shop.xianbao.modules.fastNavigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.fastNavigation.dao.FastNavigationDao;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationDTO;
import shop.xianbao.modules.fastNavigation.dto.FastNavigationListDTO;
import shop.xianbao.modules.fastNavigation.dto.IndexFastNavigationDTO;
import shop.xianbao.modules.fastNavigation.entity.FastNavigationEntity;
import shop.xianbao.modules.fastNavigation.service.FastNavigationService;

import java.util.ArrayList;
import java.util.List;
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
        // 这里根据业务自定义查询条件

        QueryWrapper<FastNavigationEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public IndexFastNavigationDTO getIndexFastNavigation() {
        IndexFastNavigationDTO navigationListDTOS = new IndexFastNavigationDTO();
        QueryWrapper<FastNavigationEntity> navWrapper = new QueryWrapper<>();
        navWrapper.eq("type", 1);
        navWrapper.eq("show_flag", 1);
        navWrapper.orderByAsc("sort");
        List<FastNavigationEntity> navigationEntityList = this.selectList(navWrapper);
        if (CollectionUtils.isNotEmpty(navigationEntityList)) {
            List<FastNavigationListDTO> navigationList = new ArrayList<>();
            navigationEntityList.stream().forEach(e -> {
                navigationListDTOS.setLayoutType(e.getLayoutType());
                navigationList.add(ConvertUtils.sourceToTarget(e, FastNavigationListDTO.class));
            });
            navigationListDTOS.setNavigationList(navigationList);
        }
        return navigationListDTOS;
    }
}