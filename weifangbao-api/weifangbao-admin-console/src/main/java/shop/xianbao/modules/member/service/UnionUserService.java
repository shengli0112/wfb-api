package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.member.dto.UnionUserDTO;
import shop.xianbao.modules.member.dto.UnionUserListDTO;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;

import java.math.BigDecimal;

/**
 * 基础用户表（登录验证）
 *
 * @author will
 * @since 1.0.0 2019-03-04
 */
public interface UnionUserService extends XianbaoCrudService<UnionUserEntity, UnionUserDTO, UnionUserListDTO> {

}