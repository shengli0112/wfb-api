/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import shop.xianbao.common.annotation.DataFilter;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.interceptor.DataScope;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.security.user.SecurityUser;

import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author Tim tim@ruitukeji.com
 */
@Aspect
@Component
public
class DataFilterAspect{
    
    @Pointcut("@annotation(shop.xianbao.common.annotation.DataFilter)")
    public
    void dataFilterCut(){
    
    }
    
    @Before("dataFilterCut()")
    public
    void dataFilter(JoinPoint point){
        Object params = point.getArgs()[0];
        if(params != null && params instanceof Map){
            MemberDataEntity user = SecurityUser.getUser();
            
            //如果不是超级管理员，则进行数据过滤
            Map    map       = (Map) params;
            String sqlFilter = getSqlFilter(user, point);
            map.put(Constant.SQL_FILTER, new DataScope(sqlFilter));
            return;
        }
        
        throw new XianbaoException(ErrorCode.DATA_SCOPE_PARAMS_ERROR);
    }
    
    /**
     * 获取数据过滤的SQL
     */
    private
    String getSqlFilter(MemberDataEntity user, JoinPoint point){
        MethodSignature signature  = (MethodSignature) point.getSignature();
        DataFilter      dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias += ".";
        }
        
        StringBuilder sqlFilter = new StringBuilder();
        
        //查询条件前缀
        String prefix = dataFilter.prefix();
        if(StringUtils.isNotBlank(prefix)){
            sqlFilter.append(" ").append(prefix);
        }
        
        sqlFilter.append(" (");
        
        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getId());
        
        sqlFilter.append(")");
        
        return sqlFilter.toString();
    }
}