/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service;


import shop.xianbao.common.entity.SysParamsEntity;


/**
 * 参数管理
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public
interface ParamsService extends BaseService<SysParamsEntity>{
    
    void delete(Long[] ids);
    
    /**
     * 根据参数编码，获取参数的value值
     *
     * @param paramCode 参数编码
     */
    String getValue(String paramCode);
    
    /**
     * 根据参数编码，获取value的Object对象
     *
     * @param paramCode 参数编码
     * @param clazz     Object对象
     */
    <T> T getValueObject(String paramCode, Class<T> clazz);
    
    /**
     * 根据参数编码，更新value
     *
     * @param paramCode  参数编码
     * @param paramValue 参数值
     */
    int updateValueByCode(String paramCode, String paramValue);
}
