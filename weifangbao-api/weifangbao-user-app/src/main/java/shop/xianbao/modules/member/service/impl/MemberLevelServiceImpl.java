package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberLevelDao;
import shop.xianbao.modules.member.dto.MemberLevelDTO;
import shop.xianbao.modules.member.entity.MemberLevelEntity;
import shop.xianbao.modules.member.service.MemberLevelService;

import java.util.Arrays;
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
     * @return
     */
    @Override
    public PageData<MemberLevelDTO> page(Map<String, Object> params) {
        IPage<MemberLevelEntity> page = getPage(params, Constant.CREATE_DATE, false);
        List<MemberLevelEntity> list = baseDao.getList(params);
        return getPageData(list, page.getTotal(), MemberLevelDTO.class);
    }

    /**
     * 保存会员等级详情信息
     *
     * @param dto
     */
    @Override
    public void save(MemberLevelDTO dto) {
        MemberLevelEntity memberLevelEntity = ConvertUtils.sourceToTarget(dto, MemberLevelEntity.class);
        baseDao.insert(memberLevelEntity);
    }

    /**
     * 保存会员等级详情信息
     *
     * @param dto
     */
    @Override
    public void update(MemberLevelDTO dto) {
        MemberLevelEntity memberLevelEntity = ConvertUtils.sourceToTarget(dto, MemberLevelEntity.class);
        baseDao.updateById(memberLevelEntity);
    }

    /**
     * 删除会员等级详情信息
     *
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        //逻辑删除
        //TODO 逻辑删除处理逻辑
        deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 查询会员规则等级信息
     *
     * @return
     */
    @Override
    public List<MemberLevelDTO> queryMemberLevel() {
        QueryWrapper<MemberLevelEntity> params = new QueryWrapper<>();
        params.eq("is_deleted", 0);
        List<MemberLevelEntity> entity = baseDao.selectList(params);
        return ConvertUtils.sourceToTarget(entity, MemberLevelDTO.class);
    }
}