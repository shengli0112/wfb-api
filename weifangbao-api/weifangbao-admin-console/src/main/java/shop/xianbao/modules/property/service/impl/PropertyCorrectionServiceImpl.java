package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.property.dao.PropertyCorrectionDao;
import shop.xianbao.modules.property.dto.PropertyCorrectionDTO;
import shop.xianbao.modules.property.dto.PropertyCorrectionListDTO;
import shop.xianbao.modules.property.entity.PropertyCorrectionEntity;
import shop.xianbao.modules.property.service.PropertyCorrectionService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 楼盘纠错表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Service
public class PropertyCorrectionServiceImpl extends BaseServiceImpl<PropertyCorrectionDao, PropertyCorrectionEntity> implements PropertyCorrectionService {
    @Resource
    private PropertyCorrectionDao propertyCorrectionDao;

    @Override
    public PageData<PropertyCorrectionListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "create_date", false);
        List<PropertyCorrectionListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), PropertyCorrectionListDTO.class);
    }

    @Override
    public List<PropertyCorrectionListDTO> list(Map<String, Object> params) {
        List<PropertyCorrectionListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<PropertyCorrectionDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<PropertyCorrectionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<PropertyCorrectionEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, PropertyCorrectionDTO.class);
    }

    @Override
    public PropertyCorrectionDTO get(Long id) {
        return propertyCorrectionDao.getInfo(id);
    }

    @Override
    public boolean add(PropertyCorrectionDTO dto) {
        PropertyCorrectionEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCorrectionEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(PropertyCorrectionDTO dto) {
        PropertyCorrectionEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCorrectionEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public Boolean updateStatus(PropertyCorrectionDTO dto) {
        PropertyCorrectionEntity entity = new PropertyCorrectionEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        return updateById(entity);
    }
}