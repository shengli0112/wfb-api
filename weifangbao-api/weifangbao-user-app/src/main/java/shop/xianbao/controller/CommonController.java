/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.dto.SendCaptchaDTO;
import shop.xianbao.dto.SysDictDTO;
import shop.xianbao.modules.appversion.dto.SysAppVersionDTO;
import shop.xianbao.modules.appversion.service.SysAppVersionService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.setting.dto.AreaListDTO;
import shop.xianbao.modules.setting.dto.AreaSmallDTO;
import shop.xianbao.modules.setting.service.AreaService;
import shop.xianbao.modules.sys.service.SysDictService;
import shop.xianbao.modules.sys.service.SysParamsService;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;
import shop.xianbao.service.ApiCaptchaService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 注册接口
 *
 * @author Tim tim@ruitukeji.com
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ApiCaptchaService captchaService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UnionUserService unionUserService;

    @Autowired
    private SysAppVersionService sysAppVersionService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysParamsService sysParamsService;

    /**
     * 获取图形验证码图片
     */
    @GetMapping("ivCode")
    public void ivCode(HttpServletResponse response, String ivId) throws IOException {

        //ivid不能为空
        AssertUtils.isBlank(ivId, ErrorCode.REQUEST_PARAM_MISSING, "ivId");
        captchaService.responseIvImg(response, ivId);

    }

    /**
     * 发送短信验证码
     */
    @PostMapping("sendSmsCaptcha")
    public Result<String> sendSmsCaptcha(@RequestBody SendCaptchaDTO dto) {
        ValidatorUtils.validateEntity(dto);
        Result<String> ret = new Result<>();
        // 图像验证码
        //if(!captchaService.checkIvCode(dto.getIvId(), dto.getIvCode())){
        //    return ret.error( ErrorCode.REQUEST_ACCESS_INVALID,"图像验证码错误");
        //}

        UnionUserEntity entity = unionUserService.getEntityByMobile(dto.getReceiver());
        switch (dto.getOpt()) {
            case 1:
            case 5:
                if (entity != null) {
                    return ret.error("The mobile already registered");
                }
                break;
            case 2:
            case 3:
            case 4:
                if (entity == null) {
                    return ret.error("The mobile unregistered");
                }
                break;
            default:
                return ret.error("opt参数错误");
        }

        captchaService.sendSmsCaptcha(dto.getReceiver(), dto.getOpt());
        return ret.ok("");
    }

    @GetMapping("/area")
    public Result<List<AreaListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AreaListDTO> list = areaService.list(params);
        Result<List<AreaListDTO>> ret = new Result<List<AreaListDTO>>();
        if (list == null || list.isEmpty()) {
            ret.error(ErrorCode.REQUEST_NO_DATA);
        }
        return ret.ok(list);
    }

    /**
     * 获取地区树状JSON数据
     */
    @GetMapping("/tree_area")
    public Result<List<AreaSmallDTO>> all_list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AreaSmallDTO> lists = areaService.allList(params);
        Result<List<AreaSmallDTO>> ret = new Result<List<AreaSmallDTO>>();
        return ret.ok(lists);
    }

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

    @GetMapping("/checkAppVersion")
    @ApiOperation("检测版本号并返回最新版本")
    public Result<SysAppVersionDTO> checkVersion(@RequestParam(value = "version", defaultValue = "") String version, @RequestParam(value = "type", defaultValue = "0") int type) {
        SysAppVersionDTO last = sysAppVersionService.checkAndReturnLast(version, type);
        Result<SysAppVersionDTO> ret = new Result<>();
        if (last == null) {
            return ret.error(ErrorCode.REQUEST_NO_DATA);
        }
        return ret.ok(last);
    }

    @GetMapping("params/{paramCode}")
    @ApiOperation("字典分类数据")
    @ApiImplicitParams({
    })
    public Result<String> getParams(@ApiIgnore @PathVariable("paramCode") String paramCode) {

        return new Result<String>().ok(sysParamsService.getValue(paramCode));
    }
}