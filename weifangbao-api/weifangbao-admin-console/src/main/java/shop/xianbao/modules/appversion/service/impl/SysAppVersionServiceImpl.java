package shop.xianbao.modules.appversion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.appversion.dao.SysAppVersionDao;
import shop.xianbao.modules.appversion.dto.SysAppVersionDTO;
import shop.xianbao.modules.appversion.dto.SysAppVersionListDTO;
import shop.xianbao.modules.appversion.entity.SysAppVersionEntity;
import shop.xianbao.modules.appversion.service.SysAppVersionService;

import java.util.Map;

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

}