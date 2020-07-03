package shop.xianbao.modules.appversion.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.appversion.entity.SysAppVersionEntity;

/**
 * @author will
 * @since 1.0.0 2019-03-22
 */
@Mapper
public interface SysAppVersionDao extends BaseDao<SysAppVersionEntity> {
    // 查找最新版本
    SysAppVersionEntity findLast(@Param("type") int appType);

    // 根据 type+version查找
    SysAppVersionEntity findByVersion(@Param("version") String version, @Param("type") int type);

    // 指定版本 是否需要强制更新
    int needForcedUpdate(@Param("sn") int sn, @Param("type") int appType);
}