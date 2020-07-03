package shop.xianbao.modules.setting.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.setting.dao.AreaDao;
import shop.xianbao.modules.setting.dto.AreaDTO;
import shop.xianbao.modules.setting.dto.AreaListDTO;
import shop.xianbao.modules.setting.dto.AreaSmallDTO;
import shop.xianbao.modules.setting.entity.AreaEntity;
import shop.xianbao.modules.setting.service.AreaService;

import java.util.List;
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
        String levelStr = (String)params.get("level");
        String nameStr = (String)params.get("name");
        //不传参时默认查pid=0
        if (StringUtils.isBlank(pidStr) && StringUtils.isBlank(levelStr) && StringUtils.isBlank(nameStr)) {
            pidStr = "0";
            Long pid = Convert.toLong(pidStr);
            wrapper.eq("pid", pid);
        }
        if (StringUtils.isNotBlank(pidStr)) {
            //            pidStr = "0";
            Long pid = Convert.toLong(pidStr);
            wrapper.eq("pid", pid);
        }
        if (StringUtils.isNotBlank(levelStr)) {
            wrapper.eq("level", levelStr);
        }
        if (StringUtils.isNotBlank(nameStr)) {
            wrapper.like("name", nameStr);
        }

        return wrapper;
    }

    public List<AreaSmallDTO> selectAreaByPid(Long pid) {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        List<AreaSmallDTO> listAreaDTO = ConvertUtils.sourceToTarget(baseDao.selectList(wrapper), AreaSmallDTO.class);
        return listAreaDTO;
    }

    @Override
    public List<AreaSmallDTO> allList(Map<String, Object> params) {
        String pidStr = (String)params.get("pid");
        if (StringUtils.isBlank(pidStr)) {
            pidStr = "0";
        }
        Long pid = Convert.toLong(pidStr);
        List<AreaSmallDTO> listProvinceDTO = selectAreaByPid(pid);
        if (listProvinceDTO == null) {
            return null;
        }
        for (AreaSmallDTO provinceDTO : listProvinceDTO) {
            List<AreaSmallDTO> listCityDTO = selectAreaByPid(provinceDTO.getId());
            if (listCityDTO == null) {
                continue;
            }
            provinceDTO.setChild_list(listCityDTO);

            for (AreaSmallDTO cityDTO : listCityDTO) {
                List<AreaSmallDTO> listTownDTO = selectAreaByPid(cityDTO.getId());
                if (listCityDTO == null) {
                    continue;
                }
                cityDTO.setChild_list(listTownDTO);
            }
        }
        return listProvinceDTO;
    }
}