/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.modules.sys.dao.SysLanguageDao;
import shop.xianbao.modules.sys.entity.SysLanguageEntity;
import shop.xianbao.modules.sys.service.SysLanguageService;

/**
 * 国际化
 *
 * @author Tim tim@ruitukeji.com
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if (baseDao.getLanguage(entity) == null) {
            baseDao.insert(entity);
        } else {
            baseDao.updateLanguage(entity);
        }
    }
}