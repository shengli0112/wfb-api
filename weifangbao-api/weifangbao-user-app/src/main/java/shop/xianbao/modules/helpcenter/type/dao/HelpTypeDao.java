package shop.xianbao.modules.helpcenter.type.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.helpcenter.type.entity.HelpTypeEntity;

/**
 * 帮助类型管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Mapper
public interface HelpTypeDao extends BaseDao<HelpTypeEntity> {

}