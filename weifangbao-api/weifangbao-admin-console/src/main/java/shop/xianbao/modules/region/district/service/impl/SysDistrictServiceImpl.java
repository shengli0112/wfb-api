package shop.xianbao.modules.region.district.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.region.district.dao.SysDistrictDao;
import shop.xianbao.modules.region.district.dto.SysDistrictListDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictSaveDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictUpdateDTO;
import shop.xianbao.modules.region.district.entity.SysDistrictEntity;
import shop.xianbao.modules.region.district.service.SysDistrictService;

import java.util.List;

/**
 * 五级地区(区或县)管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Service
public class SysDistrictServiceImpl extends BaseServiceImpl<SysDistrictDao, SysDistrictEntity> implements SysDistrictService {

    @Override
    public void save(SysDistrictSaveDTO dto) {
        SysDistrictEntity entity = ConvertUtils.sourceToTarget(dto, SysDistrictEntity.class);
        insert(entity);
    }

    @Override
    public SysDistrictUpdateDTO get(Long id) {
        SysDistrictEntity entity = baseDao.selectById(id);
        if (entity == null) {
            throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
        }

        return ConvertUtils.sourceToTarget(entity, SysDistrictUpdateDTO.class);
    }

    @Override
    public void update(SysDistrictUpdateDTO dto) {
        SysDistrictEntity entity = ConvertUtils.sourceToTarget(dto, SysDistrictEntity.class);

        checkResult(updateById(entity));
    }

    @Override
    public void delete(Long[] ids) {
        startDeleteBatch(ids);
    }

    @Override
    public List<SysDistrictListDTO> list(Long cityId) {

        QueryWrapper<SysDistrictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("city_id", cityId).orderByAsc("sort");

        List<SysDistrictEntity> list = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(list, SysDistrictListDTO.class);
    }
}