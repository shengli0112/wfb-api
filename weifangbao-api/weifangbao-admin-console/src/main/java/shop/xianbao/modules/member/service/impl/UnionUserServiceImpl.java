package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.Map;

/**
 * 基础用户表（登录验证）
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
@Service
public class UnionUserServiceImpl extends XianbaoCrudServiceImpl<UnionUserDao, UnionUserEntity, UnionUserDTO, UnionUserListDTO> implements UnionUserService {

    @Override
    public QueryWrapper<UnionUserEntity> getWrapper(Map<String, Object> params) {

        // 这里根据业务自定义查询条件

        // 这里暂且返回null,表示无条件查询
        return null;
    }



}