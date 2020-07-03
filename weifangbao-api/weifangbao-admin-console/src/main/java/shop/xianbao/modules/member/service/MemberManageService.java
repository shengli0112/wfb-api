package shop.xianbao.modules.member.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.member.entity.MemberManageEntity;

import java.util.Map;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
public interface MemberManageService extends BaseService<MemberManageEntity> {
    /**
     * 会员管理列表
     */
    public PageData<MemberManageEntity> memManagerPage(Map<String, Object> params);
}