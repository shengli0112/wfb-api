package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.member.dto.EditUserInfoDTO;
import shop.xianbao.modules.member.dto.MemberBaseinfoDTO;
import shop.xianbao.modules.member.dto.MemberDTO;
import shop.xianbao.modules.member.entity.MemberBaseinfoEntity;

/**
 * 会员信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
public interface MemberBaseinfoService extends BaseService<MemberBaseinfoEntity> {

    MemberDTO queryMemberInfo(Long id);

    /**
     * 更新会员信息
     *
     * @param dto 用户修改信息
     */
    void updateMmeberInfo(EditUserInfoDTO dto);

    /**
     * 新增会员信息
     *
     * @param dto
     */
    void saveMmeberInfo(MemberBaseinfoDTO dto);
}