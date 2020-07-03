package shop.xianbao.modules.member.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dto.EditMemberDTO;
import shop.xianbao.modules.member.dto.MemberUserDTO;
import shop.xianbao.modules.member.dto.MemberUserListDTO;
import shop.xianbao.modules.member.dto.UserMemberDTO;
import shop.xianbao.modules.member.entity.MemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
public interface MemberService extends BaseService<MemberEntity> {

    /**
     * 查询用户是否存在
     *
     * @param userName 用户名
     * @return
     */
    Result queryMemberInfo(String userName);

    /**
     * 逻辑删除用户
     *
     * @param ids
     */
    void deletedUser(List<Long> ids);

    List<MemberEntity> queryBathMemberInfo(List<Long> ids);

    /**
     * 更改会员账户状态
     *
     * @param dto
     */
    void updateMmeberStatus(EditMemberDTO dto);

    /**
     * @return
     */
    List<UserMemberDTO> getAllMemberId();

    PageData<MemberUserListDTO> page(Map<String, Object> params);

    Result<MemberUserDTO> get(Long id);

    boolean updateById(MemberUserDTO id);
}