package shop.xianbao.modules.helpcenter.content.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.helpcenter.content.entity.HelpContentEntity;

import java.util.List;
import java.util.Map;

/**
 * 帮助内容表
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Mapper
public interface HelpContentDao extends BaseDao<HelpContentEntity> {

    List<HelpContentEntity> getList(Map<String, Object> params);
}