package shop.xianbao.modules.message.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.SmsMessageServiceImpl;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: ApiSmsMessageServiceImpl
 * @description:
 * @author: yh
 * @create: 2019-11-28 18:11
 **/
@Service
public class ApiSmsMessageServiceImpl extends SmsMessageServiceImpl implements ApiSmsMessageService {
    @Async
    @Override
    public void sendReportMessage(List<ReportListDTO> smsList) {
        sendMessage(smsList, OptEnum.REPORT);
    }

    @Async
    @Override
    public void sendInvalidMessage(List<ReportListDTO> smsList) {
        sendMessage(smsList, OptEnum.REPORT_INVALID);
    }

    @Async
    @Override
    public void sendWillInvalidMessage(List<ReportListDTO> smsList) {
        sendMessage(smsList, OptEnum.REPORT_WILL_INVALID);

    }

    @Async
    @Override
    public void sendInviteMessage(ReportDTO reportDTO) {
        sendSmsMessage(reportDTO.getReportMobile(), reportDTO.getCustomerName(), reportDTO.getMobile(), reportDTO.getPropertyName(), OptEnum.INVITE);
    }

    @Async
    @Override
    public void sendAuthMessage(String mobile, String tel) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("tel", tel);
        sendSmsMessage(mobile, params, OptEnum.ATUH);
    }

    /**
     *
     * @param mobileList
     * @param propertyDTO
     * @param tel
     * 【微房宝】微房宝提醒您，微房宝#project#，#area#,#price#，#commission#，#param#；#tel#
     * 【微房宝】微房宝提醒您，微房宝新进项目#project#，核心地段优质产品，面积段#area#,均价#price#，佣金#commission#，火速结佣；详询专属市场专员或来电咨询#tel#
     */
    @Async
    @Override
    public void sendPropertyMessage(List<String> mobileList, PropertyDTO propertyDTO, String tel) {
        if (CollectionUtils.isNotEmpty(mobileList)) {
            mobileList.forEach(mobile -> sendSmsMessage(mobile,
                "新进项目"+propertyDTO.getPropertyName(),
                "核心地段优质产品，面积段"+propertyDTO.getMinBuildArea() + "-" + propertyDTO.getMaxBuildArea(),
                "均价"+propertyDTO.getUnitPrice().toString(),
               "佣金"+ propertyDTO.getCommission(),
                "火速结佣",
                "详询专属市场专员或来电咨询"+tel,
                OptEnum.NEW_PROPERTY));
        }
    }

    @Async
    @Override
    public void sendMessage(List<ReportListDTO> smsList, OptEnum opt) {
        if (CollectionUtils.isNotEmpty(smsList)) {
            for (ReportListDTO e : smsList) {
                sendSmsMessage(e.getReportMobile(), e.getCustomerName(), e.getMobile(), e.getPropertyName(), opt);
            }
        }
    }
}
