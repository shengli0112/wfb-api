package shop.xianbao.modules.member.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dto.UserAuthCheckDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

import java.util.Map;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
public interface UserAuthenticationService extends XianbaoCrudService<UserAuthenticationEntity, UserAuthenticationDTO, UserAuthenticationListDTO> {

    PageData<UserAuthenticationListDTO> selectUserAuthPage(Map<String, Object> params);

    UserAuthenticationDTO selectUserAuthById(Long id);

    Result check(UserAuthCheckDTO authCheckDTO);
}