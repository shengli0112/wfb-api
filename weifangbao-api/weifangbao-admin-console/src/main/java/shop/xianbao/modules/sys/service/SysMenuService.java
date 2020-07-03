/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.security.user.UserDetail;
import shop.xianbao.modules.sys.dto.SysMenuDTO;
import shop.xianbao.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    SysMenuDTO get(Long id);

    void save(SysMenuDTO dto);

    void update(SysMenuDTO dto);

    void delete(Long id);

    /**
     * 菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenuDTO> getAllMenuList(Integer type);

    /**
     * 用户菜单列表
     *
     * @param user  用户
     * @param type 菜单类型
     */
    List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type);

    /**
     * 根据父菜单，查询子菜单
     * @param pid  父菜单ID
     */
    List<SysMenuDTO> getListPid(Long pid);
}
