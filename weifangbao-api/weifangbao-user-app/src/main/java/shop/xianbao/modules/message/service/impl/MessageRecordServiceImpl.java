package shop.xianbao.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.message.dao.MessageRecordDao;
import shop.xianbao.modules.message.dto.MessageRecordDTO;
import shop.xianbao.modules.message.dto.MessageRecordListDTO;
import shop.xianbao.modules.message.entity.MessageRecordEntity;
import shop.xianbao.modules.message.service.MessageRecordService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 消息记录表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-07
 */
@Service
public class MessageRecordServiceImpl extends BaseServiceImpl<MessageRecordDao, MessageRecordEntity> implements MessageRecordService {
    @Override
    public PageData<MessageRecordListDTO> page(Map<String, Object> params) {
        Object storeIdObj = params.get("storeId");
        if (Objects.equals(null, storeIdObj) || StringUtils.isEmpty(storeIdObj.toString())) {
            throw new XianbaoException(ErrorCode.REQUEST_PARAM_MISSING);
        }
        IPage page = getPage(params, null, false);
        List<MessageRecordListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), MessageRecordListDTO.class);
    }

    @Override
    public List<MessageRecordListDTO> list(Map<String, Object> params) {
        List<MessageRecordListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<MessageRecordDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<MessageRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<MessageRecordEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, MessageRecordDTO.class);
    }

    @Override
    public MessageRecordDTO get(Long id) {
        MessageRecordEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, MessageRecordDTO.class);
    }

    @Override
    public boolean add(MessageRecordDTO dto) {
        MessageRecordEntity entity = ConvertUtils.sourceToTarget(dto, MessageRecordEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(MessageRecordDTO dto) {
        MessageRecordEntity entity = ConvertUtils.sourceToTarget(dto, MessageRecordEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }
}