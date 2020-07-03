package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.property.dao.PropertyRegionDao;
import shop.xianbao.modules.property.dto.PropertyRegionDTO;
import shop.xianbao.modules.property.dto.PropertyRegionListDTO;
import shop.xianbao.modules.property.entity.PropertyRegionEntity;
import shop.xianbao.modules.property.service.PropertyRegionService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 楼盘区域表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Service
public class PropertyRegionServiceImpl extends BaseServiceImpl<PropertyRegionDao, PropertyRegionEntity> implements PropertyRegionService {
    @Override
    public PageData<PropertyRegionListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", true);
        List<PropertyRegionListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), PropertyRegionListDTO.class);
    }

    @Override
    public List<PropertyRegionListDTO> list(Map<String, Object> params) {
        params.put("isShow", 1);
        List<PropertyRegionListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<PropertyRegionDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<PropertyRegionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<PropertyRegionEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, PropertyRegionDTO.class);
    }

    @Override
    public PropertyRegionDTO get(Long id) {
        PropertyRegionEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, PropertyRegionDTO.class);
    }

    @Override
    public boolean add(PropertyRegionDTO dto) {
        PropertyRegionEntity entity = ConvertUtils.sourceToTarget(dto, PropertyRegionEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(PropertyRegionDTO dto) {
        PropertyRegionEntity entity = ConvertUtils.sourceToTarget(dto, PropertyRegionEntity.class);
        return updateById(entity);
    }

    @Override
    public Result delete(Long[] ids) {
        Result<Object> result = new Result<>();
        UpdateWrapper<PropertyRegionEntity> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", 1);
        wrapper.in("id", ids);
        boolean update = update(new PropertyRegionEntity(), wrapper);
        if (update) {
            return result.ok();
        }
        return result.error();
    }

    @Override
    public String getAreaNameById(Long areaId) {
        PropertyRegionDTO propertyRegionDTO = get(areaId);
        if (!Objects.equals(null, propertyRegionDTO)) {
            return propertyRegionDTO.getRegionName();
        }
        return "";
    }
}