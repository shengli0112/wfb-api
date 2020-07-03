package shop.xianbao.modules.news.article.dto;

import lombok.Data;

@Data
public class ScrollingScreenDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告链接
     */
    private String url;
}
