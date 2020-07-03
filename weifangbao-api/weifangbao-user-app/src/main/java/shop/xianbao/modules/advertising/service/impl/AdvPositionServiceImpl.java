package shop.xianbao.modules.advertising.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.advertising.dao.AdvPositionDao;
import shop.xianbao.modules.advertising.dto.AdvPositionDTO;
import shop.xianbao.modules.advertising.dto.AdvPositionListDTO;
import shop.xianbao.modules.advertising.entity.AdvPositionEntity;
import shop.xianbao.modules.advertising.service.AdvPositionService;

import java.util.Map;

/**
 * 广告位表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Service
public class AdvPositionServiceImpl extends XianbaoCrudServiceImpl<AdvPositionDao, AdvPositionEntity, AdvPositionDTO, AdvPositionListDTO> implements AdvPositionService {

    @Override
    public QueryWrapper<AdvPositionEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<AdvPositionEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }
}