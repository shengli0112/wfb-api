/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Tim tim@ruitukeji.com
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

    SysMenuEntity getById(@Param("id") Long id, @Param("language") String language);

    /**
     * 查询所有菜单列表
     *
     * @param type 菜单类型
     * @param language 语言
     */
    List<SysMenuEntity> getMenuList(@Param("type") Integer type, @Param("language") String language);

    /**
     * 查询用户菜单列表
     *
     * @param userId 用户ＩＤ
     * @param type 菜单类型
     * @param language 语言
     */
    List<SysMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("type") Integer type, @Param("language") String language);

    /**
     * 查询用户权限列表
     * @param userId  用户ID
     */
    List<String> getUserPermissionsList(Long userId);

    /**
     * 查询所有权限列表
     */
    List<String> getPermissionsList();

    /**
     * 根据父菜单，查询子菜单
     * @param pid  父菜单ID
     */
    List<SysMenuEntity> getListPid(Long pid);

}
