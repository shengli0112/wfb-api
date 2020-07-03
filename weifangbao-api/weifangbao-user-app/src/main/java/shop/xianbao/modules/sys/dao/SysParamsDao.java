/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.xianbao.common.dao.ParamsDao;
import shop.xianbao.common.entity.SysParamsEntity;

import java.util.List;

/**
 * 参数管理
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@Mapper
public interface SysParamsDao extends BaseMapper<SysParamsEntity>, ParamsDao {
    /**
     * 根据参数编码，查询value
     *
     * @param paramCode 参数编码
     * @return 参数值
     */
    @Override
    String getValueByCode(String paramCode);

    /**
     * 获取参数编码列表
     *
     * @param ids ids
     * @return 返回参数编码列表
     */
    @Override
    List<String> getParamCodeList(Long[] ids);

    /**
     * 根据参数编码，更新value
     *
     * @param paramCode  参数编码
     * @param paramValue 参数值
     */
    @Override
    int updateValueByCode(@Param("paramCode") String paramCode, @Param("paramValue") String paramValue);
}
