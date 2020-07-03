package shop.xianbao.modules.region.district.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.region.district.dto.SysDistrictListDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictSaveDTO;
import shop.xianbao.modules.region.district.dto.SysDistrictUpdateDTO;
import shop.xianbao.modules.region.district.entity.SysDistrictEntity;

import java.util.List;

/**
 * 五级地区(区或县)管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
public interface SysDistrictService extends BaseService<SysDistrictEntity> {

    void save(SysDistrictSaveDTO dto);

    SysDistrictUpdateDTO get(Long id);

    void update(SysDistrictUpdateDTO dto);

    void delete(Long[] ids);

    List<SysDistrictListDTO> list(Long cityId);
}