package shop.xianbao.modules.news.article.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章是否发布对应的枚举
 */
@AllArgsConstructor
@Getter
public enum ArticleStatusEnum {

    NO(0, "未发布"), YES(1, "已发布");

    private Integer code;

    private String msg;
}
