package shop.xianbao.modules.helpcenter.content.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.helpcenter.content.dao.HelpContentDao;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentListDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentSaveDTO;
import shop.xianbao.modules.helpcenter.content.dto.HelpContentUpdateDTO;
import shop.xianbao.modules.helpcenter.content.entity.HelpContentEntity;
import shop.xianbao.modules.helpcenter.content.service.HelpContentService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 帮助内容表
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Service
public class HelpContentServiceImpl extends BaseServiceImpl<HelpContentDao, HelpContentEntity> implements HelpContentService {

    @Override
    public void save(HelpContentSaveDTO dto) {
        HelpContentEntity entity = ConvertUtils.sourceToTarget(dto, HelpContentEntity.class);
        insert(entity);
    }

    @Override
    public HelpContentUpdateDTO get(Long id) {
        HelpContentEntity entity = baseDao.selectById(id);
        if (entity == null) {
            throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
        }

        return ConvertUtils.sourceToTarget(entity, HelpContentUpdateDTO.class);
    }

    @Override
    public void update(HelpContentUpdateDTO dto) {
        HelpContentEntity entity = ConvertUtils.sourceToTarget(dto, HelpContentEntity.class);
        updateById(entity);
    }

    @Override
    public PageData<HelpContentListDTO> page(Map<String, Object> params) {

        //分页
        IPage<HelpContentEntity> page = getPage(params, "c.sort", true);

        //查询
        List<HelpContentEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), HelpContentListDTO.class);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void delete(Long[] ids) {
        baseDao.deleteBatchIds(Arrays.asList(ids));
    }
}