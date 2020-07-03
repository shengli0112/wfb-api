package shop.xianbao.modules.news.article.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.xianbao.common.entity.XianBaoBaseEntity;

import java.util.Date;

/**
 * 文章列表管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wly_article")
public class ArticleEntity extends XianBaoBaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章短标题
     */
    private String shortTitle;
    /**
     * 文章标签
     */
    private String tag;
    /**
     * 文章标题图片访问路径
     */
    private String imagePath;

    /**
     * 文章视频封面
     */
    private String videoCover;

    /**
     * 文章视频访问路径
     */
    private String videoPath;

    /**
     * 文章作者
     */
    private String author;
    /**
     * 文章来源
     */
    private String source;
    /**
     * 文章来源链接
     */
    private String url;
    /**
     * 文章分类编号
     */
    private Long classId;
    /**
     * 文章关键字
     */
    private String keyword;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章正文
     */
    private String content;
    /**
     * 文章附件路径
     */
    private String attachmentPath;
    /**
     * 状态 0-未发布,1-已发布
     */
    private Integer status;
    /**
     * 文章推荐标志0-未推荐，1-已推荐
     */
    private Integer commendFlag;
    /**
     * 文章是否允许评论1-允许，0-不允许
     */
    private Integer commentFlag;
    /**
     * 文章排序0-255
     */
    private Integer sort;
    /**
     * 文章点击量
     */
    private Integer click;
    /**
     * 文章评论数
     */
    private Integer commentCount;
    /**
     * 文章分享数
     */
    private Integer shareCount;
    /**
     * 文章点赞数
     */
    private Integer praiseCount;
    /**
     * 发布时间
     */
    private Date publicTime;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String className;
    /**
     * 文章状态值 0-未发布,1-已发布
     */
    @TableField(exist = false)
    private String statusValue;

    /**
     * 分类ID数组(多个用逗号隔开)
     */
    @TableField(exist = false)
    private String classIds;

    /**
     * 上级分类名称
     */
    @TableField(exist = false)
    private String pClassName;

    /**
     * 最终返回的分类名称
     */
    @TableField(exist = false)
    private String finalClassName;
}