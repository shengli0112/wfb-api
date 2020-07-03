package shop.xianbao.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.SmsMessageService;
import shop.xianbao.common.service.impl.SmsMessageServiceImpl;
import shop.xianbao.modules.member.service.UnionUserService;
import shop.xianbao.modules.property.entity.PropertyEntity;
import shop.xianbao.modules.property.service.PropertyService;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.unionuser.entity.UnionUserEntity;
import shop.xianbao.service.ApiSmsMessageService;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: ApiSmsMessageServiceImpl
 * @description:
 * @author: yh
 * @create: 2019-11-28 18:11
 **/
@Service
public
class ApiSmsMessageServiceImpl extends SmsMessageServiceImpl implements ApiSmsMessageService{

    @Autowired
    private PropertyService  propertyService;
    @Autowired
    private UnionUserService unionUserService;

    @Async
    @Override
    public
    void sendReportMessage(List<ReportListDTO> smsList){
        sendMessage(smsList, SmsMessageService.OptEnum.REPORT);
    }

    @Override
    public
    void sendMessage(List<ReportListDTO> smsList, OptEnum opt){
        if(CollectionUtils.isNotEmpty(smsList)){
            for(ReportListDTO e : smsList){
                sendSmsMessage(e.getReportMobile(), e.getCustomerName(), e.getMobile(), e.getPropertyName(), opt);
            }
        }
    }

    @Async
    @Override
    public void sendInviteMessage(ReportDTO reportDTO) {
        sendSmsMessage(reportDTO.getReportMobile(), reportDTO.getCustomerName(), reportDTO.getMobile(), reportDTO.getPropertyName(), OptEnum.INVITE);
    }

    /**
     * 给楼盘管理员发短信
     */
    @Override
    public
    void sendReportMessageToMng(List<ReportListDTO> smsList){
        if (CollectionUtils.isNotEmpty(smsList)){
            for(ReportListDTO e : smsList){
                PropertyEntity property = propertyService.selectById(e.getPropertyId());
                if(property == null || property.getUnionId() == null){
                    continue;
                }

                List<UnionUserEntity> userEntities = unionUserService.selectByIds(property.getUnionId());
                for(UnionUserEntity userEntity : userEntities){
                    if(userEntity == null || userEntity.getMobile() == null){
                        continue;
                    }
                    //211536  微房宝楼盘管理人请注意，新增报备来啦，（经纪人#brokerName#报备客户#customerName#，电话#customerMobile#），请及时跟进！
                    LinkedHashMap<String, String> params = new LinkedHashMap<>();
                    params.put("brokerName", e.getNickname());
                    params.put("customerName", e.getCustomerName());
                    params.put("customerMobile", e.getMobile());
                    sendSmsMessage(userEntity.getMobile(), params, OptEnum.REPORT_TO_MNG);
                }

            }
        }


    }
}
