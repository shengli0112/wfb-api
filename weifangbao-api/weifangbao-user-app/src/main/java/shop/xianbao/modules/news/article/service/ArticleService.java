package shop.xianbao.modules.news.article.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.news.article.dto.ArticleDTO;
import shop.xianbao.modules.news.article.dto.ArticleListDTO;
import shop.xianbao.modules.news.article.dto.ArticleUpdateDTO;
import shop.xianbao.modules.news.article.dto.ScrollingScreenDTO;
import shop.xianbao.modules.news.article.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

/**
 * 文章列表管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
public interface ArticleService extends BaseService<ArticleEntity> {

    PageData<ArticleListDTO> page(Map<String, Object> params);

    void save(ArticleDTO dto);

    ArticleUpdateDTO get(Long id);

    void update(ArticleUpdateDTO dto);

    void delete(Long[] ids);

    List<ScrollingScreenDTO> getscrollingScreenList(Map<String, Object> params, String configNewsUrl);

    /**
     * 获取推荐的文章
     *
     * @return
     */
    List<ArticleListDTO> getRecommendArticle();
}