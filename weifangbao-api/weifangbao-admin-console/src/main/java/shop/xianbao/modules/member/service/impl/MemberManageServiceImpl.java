package shop.xianbao.modules.member.service.impl;

import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.page.PageUtil;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.member.dao.MemberManageDao;
import shop.xianbao.modules.member.entity.MemberManageEntity;
import shop.xianbao.modules.member.service.MemberManageService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 运营端会员管理
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Service
public class MemberManageServiceImpl extends BaseServiceImpl<MemberManageDao, MemberManageEntity> implements MemberManageService {

    @Resource
    private MemberManageDao memberManageDao;

    /**
     * 会员管理列表
     */
    @Override
    public PageData<MemberManageEntity> memManagerPage(Map<String, Object> params) {
        PageUtil.getPageParam(params);
        int total = memberManageDao.memManagePageCount(params);
        List<MemberManageEntity> list = memberManageDao.memManagePage(params);
        PageData<MemberManageEntity> pd = new PageData<>(list, total);
        return pd;
    }

    /**
     *
     */

}