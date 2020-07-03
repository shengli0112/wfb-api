package shop.xianbao.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.message.dao.MessageUserDao;
import shop.xianbao.modules.message.dto.MessageUserDTO;
import shop.xianbao.modules.message.dto.MessageUserListDTO;
import shop.xianbao.modules.message.entity.MessageEntity;
import shop.xianbao.modules.message.entity.MessageUserEntity;
import shop.xianbao.modules.message.service.MessageUserService;
import shop.xianbao.modules.report.dto.ReportDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 用户消息表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-10-22
 */
@Service
public class MessageUserServiceImpl extends BaseServiceImpl<MessageUserDao, MessageUserEntity> implements MessageUserService {
    @Override
    public PageData<MessageUserListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
        List<MessageUserListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), MessageUserListDTO.class);
    }

    @Override
    public List<MessageUserListDTO> list(Map<String, Object> params) {
        List<MessageUserListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<MessageUserDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<MessageUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<MessageUserEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, MessageUserDTO.class);
    }

    @Override
    public MessageUserDTO get(Long id) {
        MessageUserEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, MessageUserDTO.class);
    }

    @Override
    public boolean add(MessageUserDTO dto) {
        MessageUserEntity entity = ConvertUtils.sourceToTarget(dto, MessageUserEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(MessageUserDTO dto) {
        MessageUserEntity entity = ConvertUtils.sourceToTarget(dto, MessageUserEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }
}