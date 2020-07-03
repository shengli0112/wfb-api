package shop.xianbao.modules.news.articleclass.dto;

import lombok.Data;

/**
 * 文章分类列表DTO
 */
@Data
public class ArticleClassListDTO {

    private Long id;

    private String name;

    private Integer sort;
}
