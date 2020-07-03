/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.ConvertUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础服务类，所有Service都要继承
 *
 * @author Tim tim@ruitukeji.com
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseDao;
    /**
     * 获取分页对象
     *
     * @param params            分页查询参数
     * @param defaultOrderField 默认排序字段
     * @param isAsc        c9i      排序方式
     */
    protected IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        String pageStr=(String) params.get(Constant.PAGE);
        if (StringUtils.isNotBlank(pageStr)) {
            curPage = Long.parseLong(pageStr);
        }

        String limitStr=(String) params.get(Constant.LIMIT);
        if (StringUtils.isNotBlank(limitStr)) {
            limit = Long.parseLong(limitStr);
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(Constant.PAGE, page);

        //排序字段
        String orderField = (String) params.get(Constant.ORDER_FIELD);
        String order = (String) params.get(Constant.ORDER);

        //前端字段排序
        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            if (Constant.ASC.equalsIgnoreCase(order)) {
                return page.setAsc(orderField);
            } else {
                return page.setDesc(orderField);
            }
        }

        //默认排序
        if (isAsc) {
            page.setAsc(defaultOrderField);
        } else {
            page.setDesc(defaultOrderField);
        }

        return page;
    }

    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target) {
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);
        return new PageData<>(targetList, total);
    }

    protected <T> PageData<T> getPageData(IPage page, Class<T> target) {
        return getPageData(page.getRecords(), page.getTotal(), target);
    }

    protected Map<String, Object> paramsToLike(Map<String, Object> params, String... likes) {
        for (String like : likes) {
            String val = (String) params.get(like);
            if (StringUtils.isNotEmpty(val)) {
                params.put(like, "%" + val + "%");
            } else {
                params.put(like, null);
            }
        }
        return params;
    }

    /**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     * <p>
     * 注意！！ 该方法为 Integer 判断，不可传入 int 基本类型
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public boolean insert(T entity) {
        return BaseServiceImpl.retBool(baseDao.insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<T> entityList) {
        return insertBatch(entityList, 100);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<T> entityList, int batchSize) {
        SqlSession batchSqlSession = sqlSessionBatch();
        int i = 0;
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        try {
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        } finally {
            closeSqlSession(batchSqlSession);
        }
        return true;
    }

    @Override
    public boolean updateById(T entity) {
        return BaseServiceImpl.retBool(baseDao.updateById(entity));
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return BaseServiceImpl.retBool(baseDao.update(entity, updateWrapper));
    }

    @Override
    public  boolean updateOrInsert(T entity,Wrapper<T> wrapper){
        if(!update(entity,wrapper)){
            return insert(entity);
        }
        return update(entity,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, 30);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        SqlSession batchSqlSession = sqlSessionBatch();
        int i = 0;
        String sqlStatement = sqlStatement(SqlMethod.UPDATE_BY_ID);
        try {
            for (T anEntityList : entityList) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        } finally {
            closeSqlSession(batchSqlSession);
        }
        return true;
    }

    @Override
    public T selectById(Serializable id) {
        return baseDao.selectById(id);
    }

    @Override
    public List<T> selectList(Wrapper<T> var1) {
        return baseDao.selectList(var1);
    }

    @Override
    public T selectOne(Wrapper<T> var1) {
        return baseDao.selectOne(var1);
    }

    @Override
    public Integer selectCount(Wrapper<T> var1) {
        return baseDao.selectCount(var1);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> var1) {
        return baseDao.selectMaps(var1);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> var1) {
        return baseDao.selectObjs(var1);
    }

    @Override
    public IPage<T> selectPage(IPage<T> var1, Wrapper<T> var2) {
        return baseDao.selectPage(var1, var2);
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.delBool(baseDao.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return SqlHelper.delBool(baseDao.deleteBatchIds(idList));
    }

    /**
     * 删除存在上下级关系的记录时,检查子记录是否为空,
     * 为空,则可删;
     * 否则,必须删除子记录
     *
     * @param childRecordCount 子记录数量
     * @param pids             父级记录ID数组
     */
    public void checkDelete(int childRecordCount, Long[] pids) {
        if (childRecordCount > 0) {
            throw new XianbaoException(ErrorCode.CHILD_RECORD_EXISTS);
        } else {
            startDeleteBatch(pids);
        }
    }

    /**
     * 开始执行批量删除逻辑
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    public void startDeleteBatch(Long[] ids){
        int result = baseDao.deleteBatchIds(Arrays.asList(ids));
        if (result == 0){
            throw new XianbaoException(ErrorCode.DB_RECORD_OPERATION_FAILED);
        }
    }

    /**
     * 检验CRUD结果
     */
    public void checkResult(boolean result) {
        if (!result) {
            throw new XianbaoException(ErrorCode.DB_RECORD_OPERATION_FAILED);
        }
    }

}