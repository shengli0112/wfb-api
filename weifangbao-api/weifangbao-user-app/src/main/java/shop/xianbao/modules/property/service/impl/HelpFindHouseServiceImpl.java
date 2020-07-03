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
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dao.HelpFindHouseDao;
import shop.xianbao.modules.property.dto.HelpFindHouseDTO;
import shop.xianbao.modules.property.dto.HelpFindHouseListDTO;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.entity.HelpFindHouseEntity;
import shop.xianbao.modules.property.service.HelpFindHouseService;
import shop.xianbao.modules.property.service.PropertyRegionService;

import java.math.BigDecimal;
import java.util.*;

/**
 * 帮找房表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-15
 */
@Service
public class HelpFindHouseServiceImpl extends BaseServiceImpl<HelpFindHouseDao, HelpFindHouseEntity> implements HelpFindHouseService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PropertyRegionService propertyRegionService;

    @Override
    public PageData<HelpFindHouseListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "sort", false);
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
    public Result add(HelpFindHouseDTO dto, MemberDataEntity user) {
        Result result = new Result();
        HelpFindHouseEntity entity = ConvertUtils.sourceToTarget(dto, HelpFindHouseEntity.class);
        entity.setUnionId(user.getUnionId());
        HelpFindHouseDTO helpFindHouseDTO = getInfo(user);
        if (!Objects.equals(null, helpFindHouseDTO)) {
            entity.setId(helpFindHouseDTO.getId());
            boolean update = updateById(entity);
            if (update) {
                return result.ok();
            }
        }
        boolean insert = insert(entity);
        if (insert) {
            return result.ok();
        }
        return result.error();
    }

    @Override
    public boolean update(HelpFindHouseDTO dto) {
        HelpFindHouseEntity entity = new HelpFindHouseEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public HelpFindHouseDTO getInfo(MemberDataEntity user) {
        QueryWrapper<HelpFindHouseEntity> wrapper = new QueryWrapper();
        wrapper.eq("union_id", user.getUnionId());
        HelpFindHouseEntity helpFindHouseEntity = selectOne(wrapper);
        if (!Objects.equals(null, helpFindHouseEntity)) {
            HelpFindHouseDTO helpFindHouseDTO = ConvertUtils.sourceToTarget(helpFindHouseEntity, HelpFindHouseDTO.class);
            helpFindHouseDTO.setAreaName(propertyRegionService.getAreaNameById(helpFindHouseDTO.getAreaId()));
            return helpFindHouseDTO;
        }
        return null;
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