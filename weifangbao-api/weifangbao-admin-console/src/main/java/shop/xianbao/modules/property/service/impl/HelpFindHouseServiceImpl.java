package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.CaptchaService;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.sms.AbstractSmsService;
import shop.xianbao.common.sms.SmsFactory;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberDao;
import shop.xianbao.modules.member.dto.MemberUserListDTO;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.property.dao.HelpFindHouseDao;
import shop.xianbao.modules.property.dto.HelpFindHouseDTO;
import shop.xianbao.modules.property.dto.HelpFindHouseListDTO;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.entity.HelpFindHouseEntity;
import shop.xianbao.modules.property.service.HelpFindHouseService;
import shop.xianbao.modules.sys.service.SysParamsService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Service
public class HelpFindHouseServiceImpl extends BaseServiceImpl<HelpFindHouseDao, HelpFindHouseEntity> implements HelpFindHouseService {

    @Resource
    private MemberDao memberDao;

    @Autowired
    private SysParamsService sysParamsService;

    @Autowired
    private ApiSmsMessageService apiSmsMessageService;

    @Autowired
    private MessageService messageService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public PageData<HelpFindHouseListDTO> page(Map<String, Object> params) {
        Object apartmentObj = params.get("apartment");
        if (!Objects.equals(null, apartmentObj) && StringUtils.isNotBlank(apartmentObj.toString())) {
            params.put("apartment", apartmentObj.toString().split(","));
        }
        IPage page = getPage(params, "hfh.update_date", false);
        List<HelpFindHouseListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), HelpFindHouseListDTO.class);
    }

    @Override
    public List<HelpFindHouseListDTO> list(Map<String, Object> params) {
        List<HelpFindHouseListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<HelpFindHouseDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<HelpFindHouseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<HelpFindHouseEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, HelpFindHouseDTO.class);
    }

    @Override
    public HelpFindHouseDTO get(Long id) {
        HelpFindHouseEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, HelpFindHouseDTO.class);
    }

    @Override
    public boolean add(HelpFindHouseDTO dto) {
        HelpFindHouseEntity entity = ConvertUtils.sourceToTarget(dto, HelpFindHouseEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(HelpFindHouseDTO dto) {
        HelpFindHouseEntity entity = ConvertUtils.sourceToTarget(dto, HelpFindHouseEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Async
    @Override
    public void helpFindHouse(PropertyDTO property) {
        long startTime = System.currentTimeMillis();
        log.info("[帮找房]推送业务 开始时间：{}", startTime);
        log.debug("执行线程:" + "[" + Thread.currentThread().getId() + "]" + Thread.currentThread().getName());
        Long areaId = property.getAreaId();
        BigDecimal unitPrice = property.getUnitPrice();
        String saleApartment = property.getSaleApartment();
        log.debug("楼盘信息【areaId:{};saleApartment:{};unitPrice:{}】", areaId, saleApartment, unitPrice);
        Map<String, Object> params = new HashMap<>(3);
        params.put("areaId", areaId);
        params.put("budget", unitPrice);
        params.put("apartment", StringUtils.isNotBlank(saleApartment) ? saleApartment.split(",") : null);
        params.put("status", 1);
        List<HelpFindHouseListDTO> list = this.list(params);
        if (CollectionUtils.isNotEmpty(list)) {
            // 符合条件记录  构建短信信息列表
            log.info("符合条件数量：{}", list.size());
            // TODO 发送短信
        } else {
            log.info("无符合条件的信息");
        }
        long endTime = System.currentTimeMillis();
        log.info("[帮找房]推送业务 结束时间：{}，耗时：{} ms", endTime, (endTime - startTime));
    }

    @Async
    @Override
    public void sendPropertyMessage(PropertyDTO propertyDTO) {
        long startTime = System.currentTimeMillis();
        log.info("[楼盘上新]推送业务 开始时间：{}", startTime);
        List<MemberUserListDTO> memberList = memberDao.getList(null);
        List<String> mobileList = new ArrayList<>();
        List<Long> unionIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(memberList)) {
            mobileList = memberList.stream().map(MemberUserListDTO::getMobile).collect(Collectors.toList());
            unionIdList = memberList.stream().map(MemberUserListDTO::getId).collect(Collectors.toList());
        }
        String tel = sysParamsService.getValue("TEL");
        apiSmsMessageService.sendPropertyMessage(mobileList, propertyDTO, tel);
        messageService.sendPropertyMessage(unionIdList, propertyDTO, tel);
        long endTime = System.currentTimeMillis();
        log.info("[楼盘上新]推送业务 结束时间：{}，耗时：{} ms,推送用户数量：{}", endTime, (endTime - startTime), mobileList.size());
    }

    public boolean sendSmsProperty(String receiver, CaptchaService.OptEnum opt) {
        if (opt == null) {
            return false;
        }
        //短信服务
        AbstractSmsService service = SmsFactory.build();
        if (service == null) {
            throw new XianbaoException(ErrorCode.SMS_CONFIG);
        }

        LinkedHashMap<String, String> params = Maps.newLinkedHashMap();
        params.put("code", null);

        //发送短信
        service.sendSms(receiver, params, opt.template());
        return true;
    }
}