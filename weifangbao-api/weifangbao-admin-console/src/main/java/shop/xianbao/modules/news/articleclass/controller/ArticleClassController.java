package shop.xianbao.modules.news.articleclass.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDetailDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassListDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassTreeDTO;
import shop.xianbao.modules.news.articleclass.service.ArticleClassService;

import java.util.List;

/**
 * 文章分类管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
@RestController
@RequestMapping("/sys/articleclass")
@Api(tags = "文章分类管理")
public class ArticleClassController {

    @Autowired
    private ArticleClassService articleClassService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:articleclass:save")
    public Result save(@RequestBody ArticleClassDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        articleClassService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("详情")
    @RequiresPermissions("sys:articleclass:info")
    public Result<ArticleClassDetailDTO> get(@PathVariable("id") Long id) {
        ArticleClassDetailDTO data = articleClassService.get(id);

        return new Result<ArticleClassDetailDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:articleclass:update")
    public Result update(@RequestBody ArticleClassDetailDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        articleClassService.update(dto);

        return new Result();
    }

    @GetMapping("/list/{pid}")
    @ApiOperation("分类列表")
    @RequiresPermissions("sys:articleclass:list")
    public Result<List<ArticleClassListDTO>> list(@PathVariable("pid") Long pid) {
        List<ArticleClassListDTO> list = articleClassService.list(pid);

        return new Result<List<ArticleClassListDTO>>().ok(list);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:articleclass:delete")
    public Result delete(@RequestBody Long[] ids) {

        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        articleClassService.delete(ids);

        return new Result();
    }

    @GetMapping("/all")
    @ApiOperation("分类下拉框")
    @RequiresPermissions("sys:articleclass:all")
    public Result<List<ArticleClassTreeDTO>> getArticleClassDropdownList() {
        List<ArticleClassTreeDTO> list = articleClassService.treeList();

        return new Result<List<ArticleClassTreeDTO>>().ok(list);
    }
}