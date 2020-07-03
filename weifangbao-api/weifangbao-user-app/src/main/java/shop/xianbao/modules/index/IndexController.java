package shop.xianbao.modules.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.annotation.NoLogin;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.advertising.service.AdvService;
import shop.xianbao.modules.fastNavigation.dto.IndexFastNavigationDTO;
import shop.xianbao.modules.fastNavigation.service.FastNavigationService;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.service.PropertyService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: IndexController
 * @description: 首页controller
 * @author: yh
 * @create: 2019-05-30 16:33
 **/
@RestController
@RequestMapping("/index")
@Api(tags = "首页")
public class IndexController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private FastNavigationService fastNavigationService;

    @Autowired
    private AdvService advService;

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        HashMap<String, Object> result = new HashMap<>(7);

        //顶部轮播广告
        result.put("wheelAdvertising", advService.getAdvByPosition(1133992826806177793L));

        //导航栏
        IndexFastNavigationDTO navigationListDTOS = (IndexFastNavigationDTO)redisUtils.get("index:navigation");
        if (null == navigationListDTOS) {
            navigationListDTOS = fastNavigationService.getIndexFastNavigation();
            //存入redis
            redisUtils.set("index:navigation", navigationListDTOS, -1L);
        }
        result.put("navigation", navigationListDTOS);

        return new Result().ok(result);
    }

    @NoLogin
    @GetMapping("recommend")
    @ApiOperation("查询列表带分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段 默认值 sort", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc),默认值 desc", paramType = "query", dataType = "String")
    })
    public Result recommend(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        PageData<PropertyListDTO> page = propertyService.userPage(params, user);
        return new Result<PageData<PropertyListDTO>>().ok(page);
    }
}
