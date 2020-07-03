package shop.xianbao.modules.news.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import shop.xianbao.common.dto.XianbaoBaseDTO;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 添加文章DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Data
@ApiModel(value = "添加文章")
public class ArticleDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "{common.title.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String title;

    @ApiModelProperty(value = "文章短标题")
    private String shortTitle;

    @ApiModelProperty(value = "文章标签")
    private String tag;

    @ApiModelProperty(value = "文章标题图片路径")
    private String imagePath;

    @ApiModelProperty(value = "文章视频封面")
    private String videoCover;

    @ApiModelProperty(value = "文章视频访问路径")
    private String videoPath;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章来源")
    private String source;

    @ApiModelProperty(value = "文章来源链接")
    private String url;

    @ApiModelProperty(value = "文章分类ID数组")
    @NotBlank(message = "{article.classIds.require}", groups = {AddGroup.class, UpdateGroup.class})
    private String classIds;

    @ApiModelProperty(value = "文章关键字")
    private String keyword;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "文章正文")
    private String content;

    @ApiModelProperty(value = "文章附件路径")
    private String attachmentPath;

    @ApiModelProperty(value = "文章状态0-未发布，1-已发布")
    @Range(message = "{article.status.range}", min = 0, max = 1, groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

    @ApiModelProperty(value = "文章推荐标志0-未推荐，1-已推荐")
    @Range(message = "{article.commendFlag.range}", min = 0, max = 1, groups = {AddGroup.class, UpdateGroup.class})
    private Integer commendFlag;

    @ApiModelProperty(value = "文章是否允许评论1-允许，0-不允许")
    @Range(message = "{article.commentFlag.range}", min = 0, max = 1, groups = {AddGroup.class, UpdateGroup.class})
    private Integer commentFlag;

    @ApiModelProperty(value = "文章排序0-255")
    @Min(value = 0, message = "{common.sort.min}", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @ApiModelProperty(value = "文章点击量")
    private Integer click;

    @ApiModelProperty(value = "文章评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "文章分享数")
    private Integer shareCount;

    @ApiModelProperty(value = "文章点赞数")
    private Integer praiseCount;
}