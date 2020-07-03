package shop.xianbao.modules.news.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改文章DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Data
@ApiModel(value = "修改文章")
public class ArticleUpdateDTO extends ArticleDTO {

    @ApiModelProperty(value = "文章ID")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;

    private Date publicTime;
}
