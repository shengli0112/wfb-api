/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.sys.dto.SysRoleDTO;
import shop.xianbao.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    PageData<SysRoleDTO> page(Map<String, Object> params);

    List<SysRoleDTO> list(Map<String, Object> params);

    SysRoleDTO get(Long id);

    void save(SysRoleDTO dto);

    void update(SysRoleDTO dto);

    void delete(Long[] ids);

}
