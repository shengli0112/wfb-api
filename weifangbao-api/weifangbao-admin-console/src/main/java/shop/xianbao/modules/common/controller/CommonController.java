package shop.xianbao.modules.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.sys.dto.SysDictDTO;
import shop.xianbao.modules.sys.service.SysDictService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 公共接口
 *
 * @author will
 * @since 1.0.0 2019-03-06
 */
@RestController
@RequestMapping("/common")
@Api(tags = "")
public class CommonController {

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("dictList")
    @ApiOperation("字典分类数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String"), @ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType = "String")
    })
    public Result<List<SysDictDTO>> dictList(@ApiIgnore @RequestParam Map<String, Object> params) {
        //字典分类数据
        List<SysDictDTO> list = sysDictService.list(params);

        return new Result<List<SysDictDTO>>().ok(list);
    }
}