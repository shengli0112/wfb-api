package shop.xianbao.modules.navigation.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.navigation.content.dao.NavigationContentDao;
import shop.xianbao.modules.navigation.content.dto.NavigationContentDTO;
import shop.xianbao.modules.navigation.content.dto.NavigationContentListDTO;
import shop.xianbao.modules.navigation.content.entity.NavigationContentEntity;
import shop.xianbao.modules.navigation.content.enums.ShowFlagEnum;
import shop.xianbao.modules.navigation.content.service.NavigationContentService;

import java.util.List;
import java.util.Map;

/**
 * 导航管理-导航内容
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Service
public class NavigationContentServiceImpl extends XianbaoCrudServiceImpl<NavigationContentDao, NavigationContentEntity, NavigationContentDTO, NavigationContentListDTO>
    implements NavigationContentService {

    @Override
    public QueryWrapper<NavigationContentEntity> getWrapper(Map<String, Object> params) {

        return null;
    }

    @Override
    public PageData<NavigationContentListDTO> page(Map<String, Object> params) {

        if (StringUtils.isBlank((String)params.get(Constant.TYPE))) {
            throw new XianbaoException(ErrorCode.PARAMS_GET_ERROR);
        }

        //分页
        IPage<NavigationContentEntity> page = getPage(params, "c.sort", true);

        //查询
        List<NavigationContentEntity> list = baseDao.getListType(params);

        // 遍历List转换枚举的值(很蠢的方法！暂时还没想到优雅的方法)
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0, size = list.size(); i < size; i++) {
                NavigationContentEntity entity = list.get(i);
                if (entity.getShowFlag().equals(ShowFlagEnum.YES.getCode())) {
                    entity.setShowFlagValue(ShowFlagEnum.YES.getMsg());
                } else {
                    entity.setShowFlagValue(ShowFlagEnum.NO.getMsg());
                }
            }
        }
        return getPageData(list, page.getTotal(), NavigationContentListDTO.class);
    }
}