/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.sys.dto.SysDeptDTO;
import shop.xianbao.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

    List<SysDeptDTO> list(Map<String, Object> params);

    SysDeptDTO get(Long id);

    void save(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    /**
     * 根据部门ID，获取本部门及子部门ID列表
     * @param id   部门ID
     */
    List<Long> getSubDeptIdList(Long id);
}