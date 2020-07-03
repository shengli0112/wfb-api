package shop.xianbao.modules.news.articleclass.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * 文章分类详情DTO
 * 备注:使用于详情和修改接口
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
@Data
@ApiModel(value = "更新文章分类")
public class ArticleClassDetailDTO extends ArticleClassDTO {

    @ApiModelProperty(value = "分类编号 ")
    @NotNull(message = "{common.id.require}", groups = {UpdateGroup.class})
    private Long id;
}
