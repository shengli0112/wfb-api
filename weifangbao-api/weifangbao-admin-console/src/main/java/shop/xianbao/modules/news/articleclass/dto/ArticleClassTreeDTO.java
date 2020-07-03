package shop.xianbao.modules.news.articleclass.dto;

import io.swagger.annotations.ApiModel;
import shop.xianbao.common.utils.TreeNode;

@ApiModel(value = "文章分类树结构")
public class ArticleClassTreeDTO extends TreeNode<ArticleClassTreeDTO> {

    private String name;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
