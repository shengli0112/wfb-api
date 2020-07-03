package shop.xianbao.modules.navigation.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.navigation.content.dao.NavigationContentDao;
import shop.xianbao.modules.navigation.content.entity.NavigationContentEntity;
import shop.xianbao.modules.navigation.module.dao.NavigationModuleDao;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleListDTO;
import shop.xianbao.modules.navigation.module.dto.NavigationModuleTypeDTO;
import shop.xianbao.modules.navigation.module.entity.NavigationModuleEntity;
import shop.xianbao.modules.navigation.module.service.NavigationModuleService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航管理-模块管理
 *
 * @author wangliangyuan
 * @since 1.0.0 2019-01-09
 */
@Service
public class NavigationModuleServiceImpl extends XianbaoCrudServiceImpl<NavigationModuleDao, NavigationModuleEntity, NavigationModuleDTO, NavigationModuleListDTO> implements NavigationModuleService {

    @Resource
    private NavigationContentDao contentDao;

    @Override
    public QueryWrapper<NavigationModuleEntity> getWrapper(Map<String, Object> params) {

        String type = (String)params.get(Constant.TYPE);
        if (StringUtils.isBlank(type)) {
            throw new XianbaoException(ErrorCode.PARAMS_GET_ERROR);
        }

        QueryWrapper<NavigationModuleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByAsc("sort");
        return queryWrapper;
    }

    @Override
    public PageData<NavigationModuleListDTO> page(Map<String, Object> params) {
        return super.page(params);
    }

    @Override
    public void delete(Long[] ids) {
        QueryWrapper<NavigationContentEntity> wrapper = new QueryWrapper<>();
        wrapper.in("module_id", ids);

        // 查询选中的类型下已有的导航内容数量
        Integer count = contentDao.selectCount(wrapper);
        if (count > 0) {
            throw new XianbaoException(ErrorCode.CHILD_RECORD_EXISTS);
        } else {
            super.delete(ids);
        }
    }

    @Override
    public List<NavigationModuleTypeDTO> selectByType(String type) {
        Map<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("type", type);

        List<NavigationModuleEntity> entityList = baseDao.selectList(getWrapper(hashMap));
        return ConvertUtils.sourceToTarget(entityList, NavigationModuleTypeDTO.class);
    }
}