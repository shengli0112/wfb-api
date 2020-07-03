package shop.xianbao.modules.feedback.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.feedback.dto.FeedbackDTO;
import shop.xianbao.modules.feedback.dto.FeedbackListDTO;
import shop.xianbao.modules.feedback.entity.FeedbackEntity;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Mapper
public interface FeedbackDao extends BaseDao<FeedbackEntity> {
    List<FeedbackListDTO> getList(Map<String, Object> params);

    FeedbackDTO getInfo(Long id);
}