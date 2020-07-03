package shop.xianbao.modules.news.article.dto;

import lombok.Data;

import java.util.Date;

/**
 * 文章列表DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Data
public class ArticleListDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    private String finalClassName;

    /**
     * 文章点击量
     */
    private Integer click;

    /**
     * 文章评论数
     */
    private Integer commentCount;

    /**
     * 文章状态 0-未发布,1-已发布
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publicTime;

    /**
     * 文章排序0-255
     */
    private Integer sort;

    /**
     * 文章状态值 0-未发布,1-已发布
     */
    private String statusValue;

    /**
     * 分类名称格式:
     * 上级分类名称/下级分类名称
     */
    private String claName;
}
