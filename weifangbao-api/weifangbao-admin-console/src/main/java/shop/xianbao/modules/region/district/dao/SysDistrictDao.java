package shop.xianbao.modules.region.district.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.region.district.entity.SysDistrictEntity;

/**
 * 五级地区(区或县)管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2019-01-02
 */
@Mapper
public interface SysDistrictDao extends BaseDao<SysDistrictEntity> {

    void deleteBatchByCityIds(Long[] ids);
}