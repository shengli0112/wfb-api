package shop.xianbao.modules.feedback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.feedback.dao.FeedbackDao;
import shop.xianbao.modules.feedback.dto.FeedbackDTO;
import shop.xianbao.modules.feedback.dto.FeedbackListDTO;
import shop.xianbao.modules.feedback.entity.FeedbackEntity;
import shop.xianbao.modules.feedback.service.FeedbackService;
import shop.xianbao.modules.member.entity.MemberDataEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 意见反馈表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackDao, FeedbackEntity> implements FeedbackService {
    @Override
    public PageData<FeedbackListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
        List<FeedbackListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), FeedbackListDTO.class);
    }

    @Override
    public List<FeedbackListDTO> list(Map<String, Object> params) {
        List<FeedbackListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<FeedbackDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<FeedbackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<FeedbackEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO get(Long id) {
        FeedbackEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, FeedbackDTO.class);
    }

    @Override
    public Result add(FeedbackDTO dto, MemberDataEntity user) {
        Result result = new Result();
        FeedbackEntity entity = ConvertUtils.sourceToTarget(dto, FeedbackEntity.class);
        if (Objects.equals(null, user)) {
            entity.setReporterId(user.getUnionId());
        }
        boolean insert = insert(entity);
        if (insert) {
            return result;
        } else {
            return result.error();
        }

    }

    @Override
    public boolean update(FeedbackDTO dto) {
        FeedbackEntity entity = ConvertUtils.sourceToTarget(dto, FeedbackEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }
}