package shop.xianbao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.google.common.collect.Lists;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.entity.XianBaoBaseEntity;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.common.utils.ConvertUtils;

import java.util.List;
import java.util.Map;

/**
 * 单表CRUD基础服务类
 *
 * @author wangliangyuan
 */
public abstract
class XianbaoCrudServiceImpl<M extends BaseMapper<T>, T extends XianBaoBaseEntity, D extends XianbaoBaseDTO, L> extends BaseServiceImpl<M, T> implements XianbaoCrudService<T, D, L>{
    
    protected
    Class<D> currentDtoClass(int index){
        return ReflectionKit.getSuperClassGenericType(getClass(), index);
    }
    
    public abstract
    QueryWrapper<T> getWrapper(Map<String, Object> params);
    
    @Override
    public
    void save(D dto){
        T entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        insert(entity);
        dto.setId(entity.getId());
    }
    
    @Override
    public
    T saveAndGetEntity(D dto){
        T entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        insert(entity);
        return entity;
    }
    
    @Override
    public
    D get(Long id){
        T entity = baseDao.selectById(id);
        if(entity == null){
            //throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
            return null;
        }
        
        return ConvertUtils.sourceToTarget(entity, currentDtoClass(2));
    }
    
    @Override
    public
    void update(D dto){
        T       entity    = ConvertUtils.sourceToTarget(dto, currentModelClass());
        boolean isSuccess = updateById(entity);
        checkResult(isSuccess);
    }
    
    @Override
    public
    PageData<L> page(Map<String, Object> params){
        IPage<T> page = baseDao.selectPage(
                
                // 默认按 sort 字段升序排列
                getPage(params, Constant.SORT, true),
                
                getWrapper(params));
        
        return (PageData<L>) getPageData(page, currentDtoClass(3));
    }
    
    @Override
    public
    List<L> list(Map<String, Object> params){
        List<T> entityList = baseDao.selectList(getWrapper(params));
        return (List<L>) ConvertUtils.sourceToTarget(entityList, currentDtoClass(3));
    }
    
    @Override
    public
    void delete(Long[] ids){
        startDeleteBatch(ids);
    }
    
    @Override
    public
    void softDelete(Long[] ids){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.in("id", ids);
        softDelete(wrapper);
    }
    
    @Override
    public
    void softDelete(QueryWrapper<T> wrapper){
        List<T> entityList = selectList(wrapper);
        if(entityList == null || entityList.isEmpty()){
            return;
        }
        List<T> upEntityList = Lists.newArrayList();
        for(T entity : entityList){
            T upEntity = null;
            try{
                upEntity = currentModelClass().newInstance();
            }catch(ReflectiveOperationException e){
                continue;
            }
            upEntity.setId(entity.getId());
            upEntity.setIsDeleted(1);
            upEntityList.add(upEntity);
        }
        updateBatchById(upEntityList);
    }
}
