package shop.xianbao.modules.member.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.member.dto.MemberLevelDTO;
import shop.xianbao.modules.member.entity.MemberLevelEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员等级
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
public interface MemberLevelService extends BaseService<MemberLevelEntity> {

    /**
     * 分页查询用户等级列表
     *
     * @param params 查询参数
     * @return
     */
    PageData<MemberLevelDTO> page(Map<String, Object> params);

    /**
     * 保存会员等级详情信息
     *
     * @param dto
     */
    void save(MemberLevelDTO dto);

    /**
     * 修改会员等级详情信息
     *
     * @param dto
     */
    void update(MemberLevelDTO dto);

    /**
     * 删除会员等级详情信息
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 查询会员规则等级信息
     *
     * @return
     */
    List<MemberLevelDTO> queryMemberLevel();
}