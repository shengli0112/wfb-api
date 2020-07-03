/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.entity.MemberTokenEntity;

/**
 * 用户Token
 * 
 * @author Tim tim@ruitukeji.com
 */
@Mapper
public interface MemberTokenDao extends BaseDao<MemberTokenEntity> {
    MemberTokenEntity getByToken(String token);

    MemberTokenEntity getByMIdClient(Long userId,int client);

    //根据token更新，终端类型和最后终端id
    void updateTerminalId(MemberTokenEntity token);

    String getSellerTerminalIds(@Param("storeId") Long storeId, @Param("clientType") int clientType);

    Long getSellerId(@Param("storeId") Long storeId);
}
