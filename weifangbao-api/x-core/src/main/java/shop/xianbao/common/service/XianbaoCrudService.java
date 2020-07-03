package shop.xianbao.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import shop.xianbao.common.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * 基于shop.xianbao.common.service.CrudService类的改进：
 * 不再以同一个DTO处理CRUD四种场景
 *
 * @author wangliangyuan
 */
public
interface XianbaoCrudService<T, D, L> extends BaseService<T>{
    
    /**
     * 新增
     */
    void save(D dto);
    
    /**
     * 新增
     */
    T saveAndGetEntity(D dto);
    
    /**
     * 根据ID查询
     */
    D get(Long id);
    
    /**
     * 更新
     */
    void update(D dto);
    
    /**
     * 分页查询列表
     */
    PageData<L> page(Map<String, Object> params);
    
    /**
     * 查询列表(不分页)
     */
    List<L> list(Map<String, Object> params);
    
    void delete(Long[] ids);
    
    void softDelete(Long[] ids);

    void softDelete(QueryWrapper<T> queryWrapper);
}
