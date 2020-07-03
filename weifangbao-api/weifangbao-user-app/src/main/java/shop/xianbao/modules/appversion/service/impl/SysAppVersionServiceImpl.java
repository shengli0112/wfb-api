package shop.xianbao.modules.appversion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.appversion.dao.SysAppVersionDao;
import shop.xianbao.modules.appversion.dto.SysAppVersionDTO;
import shop.xianbao.modules.appversion.dto.SysAppVersionListDTO;
import shop.xianbao.modules.appversion.entity.SysAppVersionEntity;
import shop.xianbao.modules.appversion.service.SysAppVersionService;

import java.util.Map;
import java.util.Objects;

/**
 * @author will
 * @since 1.0.0 2019-03-22
 */
@Service
public class SysAppVersionServiceImpl extends XianbaoCrudServiceImpl<SysAppVersionDao, SysAppVersionEntity, SysAppVersionDTO, SysAppVersionListDTO> implements SysAppVersionService {

    @Override
    public QueryWrapper<SysAppVersionEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<SysAppVersionEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    /**
     * 检测版本号 并返回最新版本
     */
    @Override
    public SysAppVersionDTO checkAndReturnLast(String version, int type) {
        SysAppVersionEntity last = baseDao.findLast(type);
        if (last == null) {
            return null;
        }
        SysAppVersionEntity curr = baseDao.findByVersion(version, type);
        if (curr == null) {
            // 找不到 对应版本 强制更新到最新版本
            last.setIsForceUpdate(1);
            return ConvertUtils.sourceToTarget(last, SysAppVersionDTO.class);
        }
        if (Objects.equals(last.getVersionNumber(), version)) {
            return null;
        }
        last.setIsForceUpdate(baseDao.needForcedUpdate(curr.getVersionSn(), type));
        return ConvertUtils.sourceToTarget(last, SysAppVersionDTO.class);
    }
}