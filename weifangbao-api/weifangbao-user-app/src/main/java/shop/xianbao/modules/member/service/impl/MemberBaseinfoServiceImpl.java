package shop.xianbao.modules.member.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberBaseinfoDao;
import shop.xianbao.modules.member.dto.EditUserInfoDTO;
import shop.xianbao.modules.member.dto.MemberBaseinfoDTO;
import shop.xianbao.modules.member.dto.MemberDTO;
import shop.xianbao.modules.member.entity.MemberBaseinfoEntity;
import shop.xianbao.modules.member.service.MemberBaseinfoService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Service
public class MemberBaseinfoServiceImpl extends BaseServiceImpl<MemberBaseinfoDao, MemberBaseinfoEntity> implements MemberBaseinfoService {

    /**
     * 查询会员信息
     *
     * @param id
     * @return
     */
    @Override
    public MemberDTO queryMemberInfo(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", id);
        List<MemberDTO> memberUserEntity = baseDao.getList(params);
        if (CollectionUtils.isEmpty(memberUserEntity)) {
            return null;
        }
        return memberUserEntity.get(0);
    }

    /**
     * 更新会员信息
     *
     * @param dto 用户修改信息
     */
    @Override
    @Transactional
    public void updateMmeberInfo(EditUserInfoDTO dto) {
        MemberBaseinfoEntity memberBaseinfoEntity = ConvertUtils.sourceToTarget(dto, MemberBaseinfoEntity.class);
        memberBaseinfoEntity.setUpdateDate(new Date());
        baseDao.updateById(memberBaseinfoEntity);
    }

    /**
     * 保存会员信息
     *
     * @param dto
     */
    @Override
    @Transactional
    public void saveMmeberInfo(MemberBaseinfoDTO dto) {
        MemberBaseinfoEntity memberBaseinfoEntity = ConvertUtils.sourceToTarget(dto, MemberBaseinfoEntity.class);
        memberBaseinfoEntity.setCreateDate(new Date());
        baseDao.insert(memberBaseinfoEntity);
    }
}