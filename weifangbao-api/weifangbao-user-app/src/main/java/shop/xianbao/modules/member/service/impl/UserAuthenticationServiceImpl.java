package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.member.dao.UserAuthenticationDao;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.member.service.UserAuthenticationService;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

import java.util.Map;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Service
public class UserAuthenticationServiceImpl extends XianbaoCrudServiceImpl<UserAuthenticationDao, UserAuthenticationEntity, UserAuthenticationDTO, UserAuthenticationListDTO>
    implements UserAuthenticationService {

    @Override
    public QueryWrapper<UserAuthenticationEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<UserAuthenticationEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }
}