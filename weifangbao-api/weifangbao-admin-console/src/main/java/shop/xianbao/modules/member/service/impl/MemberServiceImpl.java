package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dao.MemberDao;
import shop.xianbao.modules.member.dto.*;
import shop.xianbao.modules.member.entity.MemberEntity;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.security.user.SecurityUser;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员验证信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Resource
    private UnionUserService unionUserService;

    /**
     * 检查用户是否存在记录
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public Result queryMemberInfo(String userName) {
        QueryWrapper<MemberEntity> wr = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(userName)) {
            wr.eq("user_name", userName);
            wr.eq("is_deleted", 0);
        }
        MemberEntity sysUserEntity = baseDao.selectOne(wr);
        if (sysUserEntity != null) {
            //用户已经存在
            return new Result().error(ErrorCode.DB_RECORD_EXISTS);
        }
        return new Result();
    }

    /**
     * 逻辑删除用户注册信息
     *
     * @param ids 用户id组
     */
    @Override
    public void deletedUser(List<Long> ids) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("ids", ids);
        params.put("creator", SecurityUser.getUserId());
        baseDao.updateUserDeleted(params);
    }

    /**
     * 批量查询用户信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<MemberEntity> queryBathMemberInfo(List<Long> ids) {
        return baseDao.queryBathMemberInfo(ids);
    }

    /**
     * 更改会员账户状态
     *
     * @param dto 修改条件
     */
    @Override
    public void updateMmeberStatus(EditMemberDTO dto) {
        MemberEntity memberEntity = ConvertUtils.sourceToTarget(dto, MemberEntity.class);
        QueryWrapper<MemberEntity> params = new QueryWrapper<MemberEntity>();
        params.eq("is_deleted", 0);
        params.eq("id", dto.getId());
        memberEntity.setId(null);
        baseDao.update(memberEntity, params);
    }

    @Override
    public List<UserMemberDTO> getAllMemberId() {
        return memberDao.getAllMemberId();
    }

    @Override
    public PageData<MemberUserListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "create_date", false);
        List<MemberUserListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), MemberUserListDTO.class);
    }

    @Override
    public Result<MemberUserDTO> get(Long id) {
        return new Result<MemberUserDTO>().ok(memberDao.get(id));
    }

    @Override
    public
    boolean updateById(MemberUserDTO dto){
        UnionUserEntity user = unionUserService.selectById(dto.getId());
        if(user == null){
            return  false;
        }
        user.setRealname(dto.getRealname());
        return unionUserService.updateById(user);
    }
}