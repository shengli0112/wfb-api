package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dao.PropertyCorrectionDao;
import shop.xianbao.modules.property.dto.PropertyCorrectionDTO;
import shop.xianbao.modules.property.dto.PropertyCorrectionListDTO;
import shop.xianbao.modules.property.entity.PropertyCorrectionEntity;
import shop.xianbao.modules.property.service.PropertyCorrectionService;

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
    @Override
    public PageData<PropertyCorrectionListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
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
        PropertyCorrectionEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, PropertyCorrectionDTO.class);
    }

    @Override
    public Result add(PropertyCorrectionDTO dto, MemberDataEntity user) {
        Result result = new Result();
        PropertyCorrectionEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCorrectionEntity.class);
        entity.setReporterId(user.getUnionId());
        boolean insert = insert(entity);
        if (insert) {
            return result.ok();
        } else {
            return result.error();
        }
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
}