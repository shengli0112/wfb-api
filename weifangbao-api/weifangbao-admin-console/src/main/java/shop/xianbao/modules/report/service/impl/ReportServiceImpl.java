package shop.xianbao.modules.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.MobileUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.report.dao.ReportDao;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.report.entity.ReportEntity;
import shop.xianbao.modules.report.enums.ReportStatusEnum;
import shop.xianbao.modules.report.service.ReportService;

import javax.annotation.Resource;
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
    private MessageService messageService;

    @Override
    public PageData<ReportListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, "create_date", false);
        List<ReportListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), ReportListDTO.class);
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
        ReportDTO reportDTO = reportDao.getInfo(id);
        if (!Objects.equals(null, reportDTO) && !reportDTO.getStatus().equals(ReportStatusEnum.INVALID.getValue())) {
            reportDTO.setInvalidTime(null);
        }
        return reportDTO;
    }

    @Override
    public boolean add(ReportDTO dto) {
        ReportEntity entity = ConvertUtils.sourceToTarget(dto, ReportEntity.class);
        return insert(entity);
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
            ReportDTO reportDTO = get(dto.getId());
            if (reportDTO.getIsSecret().equals(1)) {
                reportDTO.setMobile(MobileUtils.secret(reportDTO.getMobile()));
            }
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

    @Override
    public List<ReportListDTO> getInvalidList() {
        List<ReportListDTO> invalidList = reportDao.getInvalidList();
        if (CollectionUtils.isNotEmpty(invalidList)) {
            invalidList.forEach(e -> e.setMobile(e.getIsSecret().equals(1) ? MobileUtils.secret(e.getMobile()) : e.getMobile()));
        }
        return invalidList;
    }

    @Override
    public List<ReportListDTO> getWillInvalidList() {
        List<ReportListDTO> willInvalidList = reportDao.getWillInvalidList();
        if (CollectionUtils.isNotEmpty(willInvalidList)) {
            willInvalidList.forEach(e -> e.setMobile(e.getIsSecret().equals(1) ? MobileUtils.secret(e.getMobile()) : e.getMobile()));
        }
        return willInvalidList;
    }

}