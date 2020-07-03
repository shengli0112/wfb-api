package shop.xianbao.modules.news.articleclass.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.news.articleclass.entity.ArticleClassEntity;

import java.util.List;

/**
 * 文章分类管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
@Mapper
public interface ArticleClassDao extends BaseDao<ArticleClassEntity> {

    List<ArticleClassEntity> getClassList();
}