package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.dao.MemberDao;
import shop.xianbao.modules.member.dao.UserAuthenticationDao;
import shop.xianbao.modules.member.dto.MemberUserDTO;
import shop.xianbao.modules.member.dto.UserAuthCheckDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationDTO;
import shop.xianbao.modules.member.dto.UserAuthenticationListDTO;
import shop.xianbao.modules.member.enums.UserAuthStatusEnums;
import shop.xianbao.modules.member.service.UserAuthenticationService;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.sys.service.SysParamsService;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户实名认证表
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Service
public class UserAuthenticationServiceImpl extends XianbaoCrudServiceImpl<UserAuthenticationDao, UserAuthenticationEntity, UserAuthenticationDTO, UserAuthenticationListDTO>
    implements UserAuthenticationService {

    @Resource
    private UserAuthenticationDao userAuthenticationDao;

    @Autowired
    private ApiSmsMessageService apiSmsMessageService;

    @Autowired
    private SysParamsService sysParamsService;

    @Autowired
    private MessageService messageService;

    @Resource
    private MemberDao memberDao;

    @Override
    public QueryWrapper<UserAuthenticationEntity> getWrapper(Map<String, Object> params) {

        QueryWrapper<UserAuthenticationEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        return wrapper;
    }

    @Override
    public PageData<UserAuthenticationListDTO> selectUserAuthPage(Map<String, Object> params) {
        IPage<UserAuthenticationEntity> iPage = getPage(params, "ua.create_date", false);
        List<UserAuthenticationListDTO> list = userAuthenticationDao.selectUserAuthPage(params);
        return new PageData<>(list, iPage.getTotal());
    }

    @Override
    public UserAuthenticationDTO selectUserAuthById(Long id) {
        return userAuthenticationDao.selectUserAuthById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result check(UserAuthCheckDTO authCheckDTO) {
        Result result = new Result();
        UserAuthenticationEntity entity = selectById(authCheckDTO.getId());
        Integer status = entity.getStatus();
        if (status.equals(UserAuthStatusEnums.Checked.getKey())) {
            return result.error("重复审核");
        }
        Integer newStatus = authCheckDTO.getStatus();
        if (newStatus.equals(UserAuthStatusEnums.Checked.getKey())) {
            // 更新unionUser的isRealAuth字段 为1
            Long unionId = entity.getUnionId();
            userAuthenticationDao.updateUnionUserAuth(unionId, 1);
            entity.setExamineTime(new Date());
            // 发送短信
            MemberUserDTO memberUserDTO = memberDao.get(unionId);
            String tel = sysParamsService.getValue("TEL");
            apiSmsMessageService.sendAuthMessage(memberUserDTO.getMobile(), tel);
            messageService.sendAuthMessage(unionId, tel);
        }
        entity.setStatus(newStatus);
        entity.setRemark(authCheckDTO.getRemark());
        updateById(entity);
        return result;
    }

}