/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.sys.dao.SysDictDao;
import shop.xianbao.modules.sys.dto.SysDictDTO;
import shop.xianbao.modules.sys.entity.SysDictEntity;
import shop.xianbao.modules.sys.service.SysDictService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageData<SysDictDTO> page(Map<String, Object> params) {
        QueryWrapper<SysDictEntity> wrapper = getWrapper(params);
        wrapper.eq("pid", Constant.DICT_ROOT);

        IPage<SysDictEntity> page = baseDao.selectPage(getPage(params, "sort", true), wrapper);

        return getPageData(page, SysDictDTO.class);
    }

    @Override
    public List<SysDictDTO> list(Map<String, Object> params) {
        List<SysDictEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysDictDTO.class);
    }

    private QueryWrapper<SysDictEntity> getWrapper(Map<String, Object> params) {
        String pid = (String)params.get("pid");
        String dictType = (String)params.get("dictType");
        String dictName = (String)params.get("dictName");
        String dictValue = (String)params.get("dictValue");

        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(pid != null, "pid", Long.parseLong(pid));
        wrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        wrapper.like(StringUtils.isNotBlank(dictName), "dict_name", dictName);
        wrapper.like(StringUtils.isNotBlank(dictValue), "dict_value", dictValue);
        wrapper.orderByAsc("sort");
        return wrapper;
    }

    @Override
    public SysDictDTO get(Long id) {
        SysDictEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDTO dto) {
        SysDictEntity entity = ConvertUtils.sourceToTarget(dto, SysDictEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

}