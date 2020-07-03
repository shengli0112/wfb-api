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
import shop.xianbao.dto.SysDictDTO;
import shop.xianbao.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public interface SysDictService extends BaseService<SysDictEntity> {

    PageData<SysDictDTO> page(Map<String, Object> params);

    List<SysDictDTO> list(Map<String, Object> params);

    SysDictDTO get(Long id);

    void save(SysDictDTO dto);

    void update(SysDictDTO dto);

    void delete(Long[] ids);
}