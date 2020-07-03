package shop.xianbao.modules.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.MobileUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.service.PropertyService;
import shop.xianbao.modules.report.dao.ReportDao;
import shop.xianbao.modules.report.dto.AddReportDTO;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.report.entity.ReportEntity;
import shop.xianbao.modules.report.enums.ReportStatusEnum;
import shop.xianbao.modules.report.service.ReportService;
import shop.xianbao.service.ApiSmsMessageService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
@Service
public class ReportServiceImpl extends BaseServiceImpl<ReportDao, ReportEntity> implements ReportService {
    @Resource
    private ReportDao reportDao;

    @Autowired
    private ApiSmsMessageService apiSmsMessageService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MessageService messageService;

    @Override
    public PageData<ReportListDTO> page(Map<String, Object> params, MemberDataEntity user) {
        params.put("unionId", user.getUnionId());
        Object statusObj = params.get("status");
        IPage page = getPage(params, "create_date", true);
        List<ReportListDTO> dtoList = baseDao.getList(params);
        handleReportList(statusObj, dtoList);
        return getPageData(dtoList, page.getTotal(), ReportListDTO.class);
    }

    /**
     * 处理报备列表字段
     *
     * @param statusObj
     * @param dtoList
     */
    private void handleReportList(Object statusObj, List<ReportListDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return;
        }
        dtoList.forEach(e -> e.setMobile(e.getIsSecret().equals(1) ? MobileUtils.secret(e.getMobile()) : e.getMobile()));
        if (statusObj != null && Integer.valueOf(statusObj.toString()).equals(ReportStatusEnum.REPORT.getValue()) && CollectionUtils.isNotEmpty(dtoList)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            //当前日期
            Integer dayOfMonth = calendar.get(Calendar.DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            StringBuilder appointTimeName;
            Integer appointDay;
            for (ReportListDTO e : dtoList) {
                calendar.setTime(e.getAppointTime());
                appointDay = calendar.get(Calendar.DATE);
                int b = appointDay - dayOfMonth;
                appointTimeName = new StringBuilder();
                if (b == 1) {
                    appointTimeName.append("明天");
                } else if (b == 0) {
                    appointTimeName.append("今天");
                } else if (b == -1) {
                    appointTimeName.append("昨天");
                } else {
                    appointTimeName.append(calendar.get(Calendar.YEAR)).append("-").append(calendar.get(Calendar.MONTH) + 1).append("-").append(appointDay);
                }
                appointTimeName.append(" ").append(sdf.format(e.getAppointTime()));
                e.setAppointTimeName(appointTimeName.toString());
            }
        }
    }

    @Override
    public List<ReportListDTO> list(Map<String, Object> params) {
        List<ReportListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<ReportDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<ReportEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<ReportEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, ReportDTO.class);
    }

    @Override
    public ReportDTO get(Long id) {
        ReportEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, ReportDTO.class);
    }

    @Override
    public Result add(ReportDTO dto, MemberDataEntity user) {
        ReportEntity entity = ConvertUtils.sourceToTarget(dto, ReportEntity.class);
        boolean insert = insert(entity);
        return new Result();
    }

    @Override
    public boolean update(ReportDTO dto) {
        ReportEntity entity = ConvertUtils.sourceToTarget(dto, ReportEntity.class);
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBatch(List<AddReportDTO> dtos, MemberDataEntity user) {
        Result result = new Result();
        if (CollectionUtils.isEmpty(dtos)) {
            return result.error(ErrorCode.REQUEST_PARAM_ILLEGAL);
        }
        List<ReportEntity> reportList = new ArrayList<>();
        List<ReportListDTO> smsList = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        Long unionId = user.getUnionId();
        dtos.forEach(e -> {
            Long propertyId = e.getPropertyId();
            PropertyDTO propertyDTO = propertyService.get(propertyId, null);
            List<AddReportDTO.ReportCustomer> reportCustomers = e.getReportCustomers();

            if (CollectionUtils.isEmpty(reportCustomers)) {
                return;
            }
            reportCustomers.forEach(rc -> {
                Long customerId = rc.getCustomerId();
                Date appointTime = rc.getAppointTime();
                String mobile = rc.getMobile();
                if (!MobileUtils.validate(mobile)) {
                    throw new XianbaoException("手机号[" + mobile + "]无效!");
                }
                // 同一个手机号不能同时 被报备
                if(isExistReport(propertyId, mobile)){
                    throw new XianbaoException("该客户[" + mobile + "]已经报备!");
                };
                calendar.setTime(appointTime);
                calendar.add(Calendar.HOUR_OF_DAY, 24);
                // 报备记录list
                reportList.add(new ReportEntity(unionId,
                    propertyId,
                    customerId,
                    rc.getCustomerName(),
                    rc.getCustomerGender(),
                    mobile,
                    rc.getIsSecret(),
                    appointTime,
                    calendar.getTime(),
                    ReportStatusEnum.REPORT.getValue()));
                // 保存数据到smsList
                ReportListDTO reportListDTO = new ReportListDTO();
                reportListDTO.setUnionId(unionId);
                reportListDTO.setReportMobile(user.getMobile());
                reportListDTO.setNickname(StringUtils.isBlank(user.getRealname())?user.getNickname():user.getRealname());
                reportListDTO.setPropertyName(propertyDTO.getPropertyName());
                reportListDTO.setPropertyId(propertyDTO.getId());
                reportListDTO.setCustomerName(rc.getCustomerName());
                reportListDTO.setCustomerGender(rc.getCustomerGender());
                reportListDTO.setMobile(rc.getIsSecret().equals(1) ? MobileUtils.secret(mobile) : mobile);
                smsList.add(reportListDTO);
            });
        });
        if (CollectionUtils.isNotEmpty(reportList)) {
            boolean b = insertBatch(reportList);
            if (b) {
                apiSmsMessageService.sendReportMessage(smsList);
                apiSmsMessageService.sendReportMessageToMng(smsList);
                messageService.sendReportMessage(unionId, smsList);
                messageService.sendReportMessageToMng(smsList);
                return result.ok();
            }
        }
        return result.error();

    }

    private
    boolean isExistReport(Long propertyId, String mobile){
        QueryWrapper<ReportEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("property_id", propertyId);
        wrapper.eq("mobile", mobile);
        wrapper.eq("status", ReportStatusEnum.REPORT.getValue());
        return baseDao.selectCount(wrapper) > 0 ;
    }

    @Override
    public ReportDTO getReportInfo(Long id) {
        ReportDTO reportInfo = reportDao.getReportInfo(id);
        if (Objects.equals(null, reportInfo)) {
            return null;
        }
        if (reportInfo.getIsSecret().equals(1)) {
            reportInfo.setMobile(MobileUtils.secret(reportInfo.getMobile()));
        }
        if (!reportInfo.getStatus().equals(ReportStatusEnum.INVALID.getValue())) {
            reportInfo.setInvalidTime(null);
        }
        return reportInfo;
    }

    @Override
    public PageData<ReportListDTO> myPage(Map<String, Object> params, MemberDataEntity user) {
        params.put("propertyUnionId", user.getUnionId());
        Object statusObj = params.get("status");
        IPage page = getPage(params, "create_date", true);
        List<ReportListDTO> dtoList = baseDao.getList(params);
        handleReportList(statusObj, dtoList);
        return getPageData(dtoList, page.getTotal(), ReportListDTO.class);
    }

    @Override
    public Result updateStatus(ReportDTO dto) {
        Result result = new Result();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(dto.getId());
        Integer status = dto.getStatus();
        reportEntity.setStatus(status);
        Date date = new Date();
        // 看房
        if (status.equals(ReportStatusEnum.INSPECTION.getValue())) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            reportEntity.setInvalidTime(calendar.getTime());
            reportEntity.setAppointTime(date);
            // 发送到访短信
            ReportDTO reportDTO = getReportInfo(dto.getId());
            apiSmsMessageService.sendInviteMessage(reportDTO);

            messageService.sendInviteMessage(reportDTO);
        }
        // 认购
        if (status.equals(ReportStatusEnum.SUBSCRIPTION.getValue())) {
            reportEntity.setSubscriptionTime(date);
        }
        // 签约
        if (status.equals(ReportStatusEnum.SIGNING.getValue())) {
            reportEntity.setSigningTime(date);
        }
        if (status.equals(ReportStatusEnum.INVALID.getValue())) {
            reportEntity.setInvalidTime(date);
        }
        boolean update = updateById(reportEntity);
        if (update) {
            return result.ok();
        }
        return result.error();
    }
}

