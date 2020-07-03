package shop.xianbao.modules.advertising.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.advertising.dao.AdvDao;
import shop.xianbao.modules.advertising.dto.AdvDTO;
import shop.xianbao.modules.advertising.dto.AdvListDTO;
import shop.xianbao.modules.advertising.entity.AdvEntity;
import shop.xianbao.modules.advertising.service.AdvService;

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

    @Autowired
    private AdvDao advDao;

    @Override
    public QueryWrapper<AdvEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<AdvEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public PageData<AdvDTO> advList(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
        List<AdvDTO> list = advDao.advList(params);

        return getPageData(list, page.getTotal(), AdvDTO.class);
    }
}