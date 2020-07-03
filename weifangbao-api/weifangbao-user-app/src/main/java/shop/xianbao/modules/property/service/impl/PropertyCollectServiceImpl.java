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
import shop.xianbao.modules.property.dao.PropertyCollectDao;
import shop.xianbao.modules.property.dto.PropertyCollectDTO;
import shop.xianbao.modules.property.dto.PropertyCollectListDTO;
import shop.xianbao.modules.property.entity.PropertyCollectEntity;
import shop.xianbao.modules.property.service.PropertyCollectService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 楼盘收藏表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-20
 */
@Service
public class PropertyCollectServiceImpl extends BaseServiceImpl<PropertyCollectDao, PropertyCollectEntity> implements PropertyCollectService {
    @Override
    public PageData<PropertyCollectListDTO> page(Map<String, Object> params, MemberDataEntity user) {
        params.put("unionId", user.getUnionId());
        IPage page = getPage(params, "pc.create_date", false);
        List<PropertyCollectListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), PropertyCollectListDTO.class);
    }

    @Override
    public List<PropertyCollectListDTO> list(Map<String, Object> params) {
        List<PropertyCollectListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<PropertyCollectDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<PropertyCollectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<PropertyCollectEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, PropertyCollectDTO.class);
    }

    @Override
    public PropertyCollectDTO get(Long id) {
        PropertyCollectEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, PropertyCollectDTO.class);
    }

    @Override
    public boolean add(PropertyCollectDTO dto, MemberDataEntity user) {
        PropertyCollectEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCollectEntity.class);
        entity.setUnionId(user.getUnionId());
        return insert(entity);
    }

    @Override
    public boolean update(PropertyCollectDTO dto) {
        PropertyCollectEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCollectEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public Result collect(PropertyCollectDTO dto, MemberDataEntity user) {
        PropertyCollectEntity entity = ConvertUtils.sourceToTarget(dto, PropertyCollectEntity.class);
        PropertyCollectDTO collectDTO = getCollect(dto.getPropertyId(), user.getUnionId());
        Result result = new Result();
        boolean flag;
        if (Objects.equals(null, collectDTO)) {
            //未收藏
            flag = add(dto, user);
        } else {
            flag = delete(new Long[] {collectDTO.getId()}) > 0;
        }
        if (flag) {
            return result.ok();
        }
        return result.error();
    }

    @Override
    public PropertyCollectDTO getCollect(Long propertyId, Long unionId) {
        QueryWrapper<PropertyCollectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("union_id", unionId);
        wrapper.eq("property_id", propertyId);
        PropertyCollectEntity propertyCollectEntity = selectOne(wrapper);
        if (Objects.equals(null, propertyCollectEntity)) {
            return null;
        } else {
            return ConvertUtils.sourceToTarget(propertyCollectEntity, PropertyCollectDTO.class);
        }
    }
}