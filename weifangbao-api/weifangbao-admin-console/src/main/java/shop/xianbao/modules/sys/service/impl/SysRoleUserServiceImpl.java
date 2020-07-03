/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.sys.dao.SysRoleUserDao;
import shop.xianbao.modules.sys.entity.SysRoleUserEntity;
import shop.xianbao.modules.sys.service.SysRoleUserService;

import java.util.List;

/**
 * 角色用户关系
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserDao, SysRoleUserEntity> implements SysRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除角色用户关系
        deleteByUserIds(new Long[] {userId});

        //用户没有一个角色权限的情况
        if (CollUtil.isEmpty(roleIdList)) {
            return;
        }

        //保存角色用户关系
        for (Long roleId : roleIdList) {
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);

            //保存
            insert(sysRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseDao.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return baseDao.getRoleIdList(userId);
    }
}