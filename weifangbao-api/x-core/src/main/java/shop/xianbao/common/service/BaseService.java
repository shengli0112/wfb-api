/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础服务接口，所有Service接口都要继承
 *
 * @author Tim tim@ruitukeji.com
 */
public interface BaseService<T> {

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     */
    boolean insert(T entity);


    /**
     * <p>
     * 插入（批量），该方法不支持 Oracle、SQL Server
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean insertBatch(Collection<T> entityList);

    /**
     * <p>
     * 插入（批量），该方法不支持 Oracle、SQL Server
     * </p>
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     */
    boolean insertBatch(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     */
    boolean updateById(T entity);

    /**
     * <p>
     * 根据 whereEntity 条件，更新记录
     * </p>
     *
     * @param entity        实体对象
     * @param updateWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    boolean update(T entity, Wrapper<T> updateWrapper);

    boolean updateOrInsert(T entity,Wrapper<T> wrapper);
    /**
     * <p>
     * 根据ID 批量更新
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * <p>
     * 根据ID 批量更新
     * </p>
     *
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    List<T> selectList(Wrapper<T> var1);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    T selectOne(Wrapper<T> var1);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    Integer selectCount(Wrapper<T> var1);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    List<Map<String, Object>> selectMaps(Wrapper<T> var1);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    List<Object> selectObjs(Wrapper<T> var1);

    /**
     * <p>
     * 根据 条件构造 查询
     * </p>
     *
     * @param var1 条件构造器
     */
    IPage<T> selectPage(IPage<T> var1, Wrapper<T> var2);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     */
    boolean deleteById(Serializable id);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     */
    boolean deleteBatchIds(Collection<? extends Serializable> idList);
    
    
    
}