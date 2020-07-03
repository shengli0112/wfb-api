package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberLevelDao;
import shop.xianbao.modules.member.dto.EditMemberLevelDTO;
import shop.xianbao.modules.member.dto.MemberLevelDTO;
import shop.xianbao.modules.member.entity.MemberLevelEntity;
import shop.xianbao.modules.member.service.MemberLevelService;

import java.util.List;
import java.util.Map;

/**
 * 会员等级
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Service
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    /**
     * 分页查询用户等级列表
     *
     * @param params 查询参数
     */
    @Override
    public PageData<MemberLevelDTO> page(Map<String, Object> params) {
        IPage<MemberLevelEntity> page = getPage(params, Constant.CREATE_DATE, true);
        List<MemberLevelEntity> list = baseDao.getList(params);
        return getPageData(list, page.getTotal(), MemberLevelDTO.class);
    }

    /**
     * 保存会员等级详情信息
     */
    @Override
    public void save(MemberLevelDTO dto) {
        MemberLevelEntity memberLevelEntity = ConvertUtils.sourceToTarget(dto, MemberLevelEntity.class);
        baseDao.insert(memberLevelEntity);
    }

    /**
     * 修改会员等级详情信息
     */
    @Override
    public void update(EditMemberLevelDTO dto) {
        MemberLevelEntity memberLevelEntity = ConvertUtils.sourceToTarget(dto, MemberLevelEntity.class);
        QueryWrapper<MemberLevelEntity> params = new QueryWrapper<>();
        params.eq("is_deleted", 0);
        params.eq("id", dto.getId());
        memberLevelEntity.setId(null);
        baseDao.update(memberLevelEntity, params);
    }

    /**
     * 删除会员等级详情信息
     *
     * @param ids 会员等级id集合
     */
    @Override
    public void delete(Long[] ids) throws XianbaoException {

    }

    /**
     * 查询会员规则等级信息
     */
    @Override
    public List<MemberLevelDTO> queryMemberLevel(Long id) {
        QueryWrapper<MemberLevelEntity> params = new QueryWrapper<>();
        if (id != null) {
            params.eq("id", id);
        }
        List<MemberLevelEntity> entity = baseDao.selectList(params);
        return ConvertUtils.sourceToTarget(entity, MemberLevelDTO.class);
    }
}