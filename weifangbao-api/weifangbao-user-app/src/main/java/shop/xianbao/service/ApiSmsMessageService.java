package shop.xianbao.service;

import shop.xianbao.common.service.SmsMessageService;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: ApiSmsMessageService
 * @description:
 * @author: yh
 * @create: 2019-11-28 18:11
 **/
public interface ApiSmsMessageService {
    void sendSmsMessage(String userMobile, LinkedHashMap<String, String> params, SmsMessageService.OptEnum opt);

    /**
     * 到访消息 1
     * 报备失效消息 2
     * 报备即将失效消息 3
     * 成功报备消息 4
     *
     * @param userMobile
     * @param customerName   客户姓名
     * @param customerMobile 客户手机
     * @param propertyName   楼盘名称
     * @param opt            消息类型
     * @return
     */
    void sendSmsMessage(String userMobile, String customerName, String customerMobile, String propertyName, SmsMessageService.OptEnum opt);

    /**
     * @param smsList
     */
    void sendReportMessage(List<ReportListDTO> smsList);

    /**
     * @param smsList
     * @param opt
     */
    void sendMessage(List<ReportListDTO> smsList, SmsMessageService.OptEnum opt);

    /**
     * 发送到访消息
     *
     * @param reportDTO
     */
    void sendInviteMessage(ReportDTO reportDTO);

    /**
     * 给楼盘管理员发短信
     * @param smsList
     */
    void sendReportMessageToMng(List<ReportListDTO> smsList);
}
