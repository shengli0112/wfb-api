/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.security.user.SecurityUser;

import java.util.Date;

/**
 * 公共字段，自动填充值
 *
 * @author Tim tim@ruitukeji.com
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createDate";
    private final static String CREATOR = "creator";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATER = "updater";
    private final static String DEPT_ID = "deptId";

    @Override
    public void insertFill(MetaObject metaObject) {
        MemberDataEntity user = SecurityUser.getUser();
        Date             date = new Date();

        //创建者
        setFieldValByName(CREATOR, user.getId(), metaObject);
        //创建时间
        setFieldValByName(CREATE_DATE, date, metaObject);
        //更新者
        setFieldValByName(UPDATER, user.getId(), metaObject);
        //更新时间
        setFieldValByName(UPDATE_DATE, date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新者
        setFieldValByName(UPDATER, SecurityUser.getUserId(), metaObject);
        //更新时间
        setFieldValByName(UPDATE_DATE, new Date(), metaObject);
    }
}