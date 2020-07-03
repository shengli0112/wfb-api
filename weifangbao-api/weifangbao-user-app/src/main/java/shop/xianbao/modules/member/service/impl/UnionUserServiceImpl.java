package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.constants.Constants;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.member.dao.UnionUserDao;
import shop.xianbao.modules.member.dto.UnionUserDTO;
import shop.xianbao.modules.member.dto.UnionUserListDTO;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.entity.MemberEntity;
import shop.xianbao.modules.member.service.MemberService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.security.password.PasswordUtils;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 基础用户表（登录验证）
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Service
public class UnionUserServiceImpl extends XianbaoCrudServiceImpl<UnionUserDao, UnionUserEntity, UnionUserDTO, UnionUserListDTO> implements UnionUserService {
    @Autowired
    private MemberService memberService;

    @Override
    public QueryWrapper<UnionUserEntity> getWrapper(Map<String, Object> params) {

        // 这里根据业务自定义查询条件

        // 这里暂且返回null,表示无条件查询
        return null;
    }

    /**
     * @param mobile 手机号
     */
    @Override
    public UnionUserEntity getEntityByMobile(String mobile) {
        QueryWrapper<UnionUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UnionUserEntity entity = selectOne(wrapper);
        return entity;
    }

    @Override
    public String getOpenId(Long unionId) {
        UnionUserEntity unionUserEntity = this.selectById(unionId);
        if (null != unionUserEntity) {
            return unionUserEntity.getOpenId();
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUserGoldAccount(Long unionId, BigDecimal newGoldAccount) {
        UnionUserEntity unionUserEntity = new UnionUserEntity();
        unionUserEntity.setId(unionId);
        unionUserEntity.setGoldCount(newGoldAccount);
        return this.updateById(unionUserEntity);
    }

    @Override
    public MemberDataEntity createMember(String phoneNumber, String openId, Long inviteId) {
        MemberDataEntity memberDataEntity = new MemberDataEntity();
        UnionUserEntity unionUserEntity = new UnionUserEntity();
        unionUserEntity.setUsername(phoneNumber);
        unionUserEntity.setNickname(phoneNumber);
        unionUserEntity.setMobile(phoneNumber);
        // 密码加密
        String salt = RandomStringUtils.randomAlphabetic(Constants.PASSWORD_SALT_SIZE);
        String password = PasswordUtils.encodeSalt("123456", salt);
        unionUserEntity.setPassword(password);
        unionUserEntity.setSalt(salt);
        unionUserEntity.setOpenId(openId);
        unionUserEntity.setGender(3);
        unionUserEntity.setIsLocked(0);
        unionUserEntity.setPId(inviteId);
        this.insert(unionUserEntity);
        Long id = unionUserEntity.getId();
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUnionId(id);
        memberEntity.setIsLocked(0);
        memberService.insert(memberEntity);
        memberDataEntity.setUnionId(id);
        memberDataEntity.setMid(memberEntity.getId());
        memberDataEntity.setUsername(unionUserEntity.getUsername());
        return memberDataEntity;
    }

    @Override
    public
    List<UnionUserEntity> selectByIds(String ids) {
        QueryWrapper<UnionUserEntity> wrapper = new QueryWrapper<>();
        wrapper.in("id", StringUtils.split(ids,","));
        List<UnionUserEntity> entities = selectList(wrapper);
        return entities;
    }
}