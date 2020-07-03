/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.dao.ParamsDao;
import shop.xianbao.common.entity.SysParamsEntity;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.redis.SysParamsRedis;
import shop.xianbao.common.service.ParamsService;

import java.util.Arrays;
import java.util.List;

/**
 * 参数管理
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public abstract
class ParamsServiceImpl extends BaseServiceImpl<ParamsDao, SysParamsEntity> implements ParamsService{
    @Autowired
    private SysParamsRedis sysParamsRedis;
    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public
    boolean insert(SysParamsEntity entity){
        boolean isOk = super.insert(entity);
        if(!isOk){
            return false;
        }
        sysParamsRedis.set(entity.getParamCode(), entity.getParamValue());
        return true;
    }
    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public
    boolean updateById(SysParamsEntity entity){
        boolean isOk = super.updateById(entity);
        if(!isOk){
            return false;
        }
        sysParamsRedis.set(entity.getParamCode(), entity.getParamValue());
        return true;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public
    void delete(Long[] ids){
        //删除Redis数据
        List<String> paramCodeList = baseDao.getParamCodeList(ids);
        String[]     paramCodes    = paramCodeList.toArray(new String[paramCodeList.size()]);
        sysParamsRedis.delete(paramCodes);
        
        //逻辑删除
        deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public
    String getValue(String paramCode){
        String paramValue = sysParamsRedis.get(paramCode);
        if(paramValue == null){
            paramValue = baseDao.getValueByCode(paramCode);
            
            sysParamsRedis.set(paramCode, paramValue);
        }
        return paramValue;
    }
    
    @Override
    public
    <T> T getValueObject(String paramCode, Class<T> clazz){
        String paramValue = getValue(paramCode);
        if(StringUtils.isNotBlank(paramValue)){
            return JSON.parseObject(paramValue, clazz);
        }
        
        try{
            return clazz.newInstance();
        }catch(Exception e){
            throw new XianbaoException(ErrorCode.PARAMS_GET_ERROR);
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public
    int updateValueByCode(String paramCode, String paramValue){
        int dbRet = baseDao.updateValueByCode(paramCode, paramValue);
        if(dbRet>0){
            sysParamsRedis.set(paramCode, paramValue);
        }
        return dbRet;
    }
    
}