package shop.xianbao.modules.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.AssertUtils;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.dto.MemberAddressDTO;
import shop.xianbao.modules.member.dto.MemberAddressListDTO;
import shop.xianbao.modules.member.entity.MemberAddressEntity;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.service.MemberAddressService;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.setting.service.AreaService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 用户收货地址表
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
@RestController
@RequestMapping("/member/memberaddress")
@Api(tags = "用户收货地址表")
public class MemberAddressController {

    @Autowired
    private MemberAddressService memberAddressService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UnionUserService unionUserService;

    @Login
    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("member:memberaddress:save")
    public Result save(@RequestBody MemberAddressDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);
        dto.setCountry(Long.parseLong("100000"));
        dto.setUnionId(user.getUnionId());
        dto.setMemberId(user.getMid());
        if (dto.getIsDefault() != null && dto.getIsDefault() == 1) {
            //当前为默认收货地址
            QueryWrapper<MemberAddressEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("union_id", user.getUnionId());
            List<MemberAddressEntity> list = memberAddressService.selectList(wrapper);
            if (list != null && list.size() > 0) {
                list.stream().forEach(memberAddressEntity -> memberAddressEntity.setIsDefault(0));
                memberAddressService.updateBatchById(list);
            }
        }
        memberAddressService.save(dto);

        return new Result();
    }

    @Login
    @GetMapping("/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("member:memberaddress:info")
    public Result<MemberAddressDTO> get(@PathVariable("id") Long id) {
        MemberAddressDTO data = memberAddressService.get(id);
        return new Result<MemberAddressDTO>().ok(data);
    }

    @Login
    @PostMapping("/update")
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("member:memberaddress:update")
    public Result update(@RequestBody MemberAddressDTO dto, @LoginUser MemberDataEntity user) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);
        dto.setUnionId(user.getUnionId());
        dto.setMemberId(user.getMid());
        if (dto.getIsDefault() != null && dto.getIsDefault() == 1) {
            //当前为默认收货地址
            QueryWrapper<MemberAddressEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("union_id", user.getUnionId());
            List<MemberAddressEntity> list = memberAddressService.selectList(wrapper);
            if (list != null) {
                list.stream().forEach(memberAddressEntity -> memberAddressEntity.setIsDefault(0));
                memberAddressService.updateBatchById(list);
            }
        }
        memberAddressService.update(dto);

        return new Result();
    }

    @Login
    @GetMapping("/page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int")
    })
    @RequiresPermissions("member:memberaddress:page")
    public Result<PageData<MemberAddressListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params, @LoginUser MemberDataEntity user) {
        params.put("union_id", user.getUnionId());
        PageData<MemberAddressListDTO> page = memberAddressService.page(params);
        if (CollectionUtils.isNotEmpty(page.getList())) {
            page.getList().stream().forEach(memberAddressListDTO -> {
                memberAddressListDTO.setLocation(areaService.get(memberAddressListDTO.getDistrict()).getMergerName().replace(",", ""));
            });
        }
        return new Result<PageData<MemberAddressListDTO>>().ok(page);
    }

    @Login
    @PostMapping("/delete")
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("member:memberaddress:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        memberAddressService.delete(ids);

        return new Result();
    }
}