package shop.xianbao.modules.news.articleclass.service;

import shop.xianbao.common.service.BaseService;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDetailDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassListDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassTreeDTO;
import shop.xianbao.modules.news.articleclass.entity.ArticleClassEntity;

import java.util.List;

/**
 * 文章分类管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
public interface ArticleClassService extends BaseService<ArticleClassEntity> {

    List<ArticleClassListDTO> list(Long pid);

    void save(ArticleClassDTO dto);

    ArticleClassDetailDTO get(Long id);

    void update(ArticleClassDetailDTO dto);

    void delete(Long[] ids);

    List<ArticleClassTreeDTO> treeList();
}