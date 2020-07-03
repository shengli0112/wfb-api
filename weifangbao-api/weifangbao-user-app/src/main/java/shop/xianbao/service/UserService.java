/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.entity.UserEntity;
import shop.xianbao.modules.member.dto.LoginDTO;

import java.util.Map;

/**
 * 用户
 * 
 * @author Tim tim@ruitukeji.com
 */
public interface UserService extends BaseService<UserEntity> {

	UserEntity getByMobile(String mobile);

	UserEntity getUserByUserId(Long userId);

	/**
	 * 用户登录
	 * @param dto    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(LoginDTO dto);
}
