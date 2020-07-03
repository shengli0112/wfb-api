package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
public interface UserAuthenticationService extends XianbaoCrudService<UserAuthenticationEntity, UserAuthenticationDTO, UserAuthenticationListDTO> {

}