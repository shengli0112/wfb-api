package shop.xianbao.modules.setting.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.setting.dto.AreaDTO;
import shop.xianbao.modules.setting.dto.AreaListDTO;
import shop.xianbao.modules.setting.entity.AreaEntity;

/**
 * 地区表/行政划分表
 *
 * @author Mark
 * @since 1.0.0 2019-02-12
 */
public interface AreaService extends XianbaoCrudService<AreaEntity, AreaDTO, AreaListDTO> {

}