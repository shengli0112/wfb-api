package shop.xianbao.modules.feedback.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.feedback.dto.FeedbackDTO;
import shop.xianbao.modules.feedback.dto.FeedbackListDTO;
import shop.xianbao.modules.feedback.entity.FeedbackEntity;
import shop.xianbao.modules.member.entity.MemberDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
public interface FeedbackService extends BaseService<FeedbackEntity> {
    PageData<FeedbackListDTO> page(Map<String, Object> params);

    List<FeedbackListDTO> list(Map<String, Object> params);

    List<FeedbackDTO> comboList(Map<String, Object> params);

    FeedbackDTO get(Long id);

    Result add(FeedbackDTO dto, MemberDataEntity user);

    boolean update(FeedbackDTO dto);

    int delete(Long[] ids);
}