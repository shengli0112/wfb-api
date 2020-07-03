package shop.xianbao.modules.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.annotation.LoginUser;
import shop.xianbao.common.annotation.LogOperation;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.common.validator.ValidatorUtils;
import shop.xianbao.common.validator.group.AddGroup;
import shop.xianbao.common.validator.group.UpdateGroup;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.member.service.UserAuthenticationService;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@RestController
@RequestMapping("/member/userauthentication")
@Api(tags = "用户实名认证表")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UnionUserService unionUserService;

    @Login
    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody UserAuthenticationDTO dto, @LoginUser MemberDataEntity user) {

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class);
        Long unionId = user.getUnionId();
        QueryWrapper<UserAuthenticationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_id", unionId);
        queryWrapper.eq("is_deleted", 0);
        UserAuthenticationEntity entity = userAuthenticationService.selectOne(queryWrapper);
        if (entity == null) {
            //新增
            entity = ConvertUtils.sourceToTarget(dto, UserAuthenticationEntity.class);
            entity.setUnionId(unionId);
            entity.setStatus(1);
            userAuthenticationService.insert(entity);
        } else {
            //更新
            entity.setCompanyName(dto.getCompanyName());
            entity.setCompanyAddress(dto.getCompanyAddress());
            entity.setCompanyPhoto(dto.getCompanyPhoto());
            entity.setBusinessLicense(dto.getBusinessLicense());
            entity.setStatus(1);
            entity.setRemark("");
            userAuthenticationService.updateById(entity);
            // 修改状态为 未认证
            UnionUserEntity unionUserEntity = new UnionUserEntity();
            unionUserEntity.setId(entity.getUnionId());
            unionUserEntity.setIsRealAuth(0);
            unionUserService.updateById(unionUserEntity);
        }
        return new Result();
    }

    @Login
    @GetMapping("info")
    @ApiOperation("信息")
    public Result<UserAuthenticationDTO> get(@ApiIgnore @LoginUser MemberDataEntity user) {
        Long unionId = user.getUnionId();
        QueryWrapper<UserAuthenticationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_id", unionId);
        queryWrapper.eq("is_deleted", 0);
        UserAuthenticationEntity entity = userAuthenticationService.selectOne(queryWrapper);
        if (entity == null) {
            return new Result().ok(new UserAuthenticationDTO());
        } else {
            UserAuthenticationDTO userAuthenticationDTO = ConvertUtils.sourceToTarget(entity, UserAuthenticationDTO.class);
            return new Result().ok(userAuthenticationDTO);
        }
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("member:userauthentication:update")
    public Result update(@RequestBody UserAuthenticationDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class);

        userAuthenticationService.update(dto);

        return new Result();
    }

}