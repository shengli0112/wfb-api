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
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.sys.dao.SysRoleDataScopeDao;
import shop.xianbao.modules.sys.entity.SysRoleDataScopeEntity;
import shop.xianbao.modules.sys.service.SysRoleDataScopeService;

import java.util.List;

/**
 * 角色数据权限
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeDao, SysRoleDataScopeEntity> implements SysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return baseDao.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色数据权限关系
        deleteByRoleIds(new Long[] {roleId});

        //角色没有一个数据权限的情况
        if (CollUtil.isEmpty(deptIdList)) {
            return;
        }

        //保存角色数据权限关系
        for (Long deptId : deptIdList) {
            SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
            sysRoleDataScopeEntity.setDeptId(deptId);
            sysRoleDataScopeEntity.setRoleId(roleId);

            //保存
            insert(sysRoleDataScopeEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }
}