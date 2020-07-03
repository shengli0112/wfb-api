package shop.xianbao.modules.helpcenter.type.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.helpcenter.content.dao.HelpContentDao;
import shop.xianbao.modules.helpcenter.content.entity.HelpContentEntity;
import shop.xianbao.modules.helpcenter.type.dao.HelpTypeDao;
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeDTO;
import shop.xianbao.modules.helpcenter.type.dto.HelpTypeListDTO;
import shop.xianbao.modules.helpcenter.type.entity.HelpTypeEntity;
import shop.xianbao.modules.helpcenter.type.service.HelpTypeService;

import java.util.List;
import java.util.Map;

/**
 * 帮助类型管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Service
public class HelpTypeServiceImpl extends BaseServiceImpl<HelpTypeDao, HelpTypeEntity> implements HelpTypeService {

    @Autowired
    private HelpContentDao helpContentDao;

    @Override
    public void save(HelpTypeDTO dto) {
        HelpTypeEntity entity = ConvertUtils.sourceToTarget(dto, HelpTypeEntity.class);
        insert(entity);
    }

    @Override
    public PageData<HelpTypeListDTO> page(Map<String, Object> params) {
        IPage<HelpTypeEntity> page = baseDao.selectPage(getPage(params, "sort", true), null);

        return getPageData(page, HelpTypeListDTO.class);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void delete(Long[] ids) {

        QueryWrapper<HelpContentEntity> wrapper = new QueryWrapper<>();
        wrapper.in("type_id", ids);
        Integer count = helpContentDao.selectCount(wrapper);

        checkDelete(count, ids);
    }

    /**
     * 查询所有帮助类别,填充类别选择下拉框
     */
    @Override
    public List<HelpTypeListDTO> selectAllType() {

        List<HelpTypeEntity> list = baseDao.selectList(null);
        return ConvertUtils.sourceToTarget(list, HelpTypeListDTO.class);
    }
}