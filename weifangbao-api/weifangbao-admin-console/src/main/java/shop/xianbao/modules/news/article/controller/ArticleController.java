package shop.xianbao.modules.news.article.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.news.article.dto.ArticleDTO;
import shop.xianbao.modules.news.article.dto.ArticleListDTO;
import shop.xianbao.modules.news.article.dto.ArticleUpdateDTO;
import shop.xianbao.modules.news.article.service.ArticleService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 文章列表管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@RestController
@RequestMapping("/sys/article")
@Api(tags = "文章列表管理")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:article:save")
    public Result save(ArticleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);

        articleService.save(dto);

        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation("详情")
    @RequiresPermissions("sys:article:info")
    public Result<ArticleUpdateDTO> get(@PathVariable("id") Long id) {
        ArticleUpdateDTO data = articleService.get(id);

        return new Result<ArticleUpdateDTO>().ok(data);
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:article:update")
    public Result update(ArticleUpdateDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        articleService.update(dto);

        return new Result();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")

    })
    @RequiresPermissions("sys:article:page")
    public Result<PageData<ArticleListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<ArticleListDTO> page = articleService.page(params);

        return new Result<PageData<ArticleListDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:article:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        articleService.delete(ids);

        return new Result();
    }
}