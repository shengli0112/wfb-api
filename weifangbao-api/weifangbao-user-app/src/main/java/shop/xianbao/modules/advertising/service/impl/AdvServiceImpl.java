package shop.xianbao.modules.advertising.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.advertising.dao.AdvDao;
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.dto.AdvListDTO;
import shop.xianbao.modules.advertising.entity.AdvEntity;
import shop.xianbao.modules.advertising.service.AdvService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 广告表
 *
 * @author songhengchong
 * @since 1.0.0 2019-05-30
 */
@Service
public class AdvServiceImpl extends XianbaoCrudServiceImpl<AdvDao, AdvEntity, AdvDTO, AdvListDTO> implements AdvService {

    @Override
    public QueryWrapper<AdvEntity> getWrapper(Map<String, Object> params) {
        Object apIdObj = params.get("apId");
        if (apIdObj == null || StringUtils.isBlank(apIdObj.toString())) {
            throw new XianbaoException("positionId不能为空");
        }
        Long apId = Long.valueOf(apIdObj.toString());
        QueryWrapper<AdvEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ap_id", apId);
        wrapper.eq("is_deleted", 0);
        wrapper.orderByDesc("create_date");
        return wrapper;
    }

    @Override
    public List<AdvListDTO> getAdvByPosition(Long positionId) {
        List<AdvListDTO> advListDTO = new ArrayList<>();
        QueryWrapper<AdvEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        wrapper.eq("ap_id", positionId);
        wrapper.orderByAsc("sort");
        List<AdvEntity> list = this.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            advListDTO = ConvertUtils.sourceToTarget(list, AdvListDTO.class);
        }
        return advListDTO;
    }
}