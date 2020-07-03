package shop.xianbao.modules.setting.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.setting.dao.AreaDao;
import shop.xianbao.modules.setting.dto.AreaDTO;
import shop.xianbao.modules.setting.dto.AreaListDTO;
import shop.xianbao.modules.setting.entity.AreaEntity;
import shop.xianbao.modules.setting.service.AreaService;

import java.util.Map;

/**
 * 地区表/行政划分表
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
@Service
public class AreaServiceImpl extends XianbaoCrudServiceImpl<AreaDao, AreaEntity, AreaDTO, AreaListDTO> implements AreaService {

    @Override
    public QueryWrapper<AreaEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();

        String pidStr = (String)params.get("pid");
        if (StringUtils.isBlank(pidStr)) {
            pidStr = "0";
        }
        Long pid = Convert.toLong(pidStr);
        wrapper.eq("pid", pid);
        return wrapper;
    }

    @Override
    public void save(AreaDTO dto) {
        dto.setStatus(1);
        if (dto.getPid() == 0L || dto.getPid() == null) {
            dto.setLevel(0);
            dto.setMergerName(dto.getName());
            super.save(dto);
            return;
        }
        AreaDTO pArea = super.get(dto.getPid());
        if (pArea == null) {
            throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
        }
        dto.setLevel(pArea.getLevel() + 1);
        dto.setMergerName(pArea.getMergerName() + "," + dto.getName());
        super.save(dto);
    }
}