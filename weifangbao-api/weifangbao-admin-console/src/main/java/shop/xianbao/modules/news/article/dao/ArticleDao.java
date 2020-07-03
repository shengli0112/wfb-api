package shop.xianbao.modules.news.article.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.news.article.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

/**
 * 文章列表管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Mapper
public interface ArticleDao extends BaseDao<ArticleEntity> {

    List<ArticleEntity> getList(Map<String, Object> params);
}