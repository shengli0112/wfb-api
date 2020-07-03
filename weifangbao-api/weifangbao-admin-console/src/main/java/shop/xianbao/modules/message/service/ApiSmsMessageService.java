package shop.xianbao.modules.message.service;

import shop.xianbao.common.service.SmsMessageService;
import shop.xianbao.modules.property.dto.PropertyDTO;
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
    void sendSmsMessage(String mobile, LinkedHashMap<String, String> params, SmsMessageService.OptEnum opt);

    /**
     * 到访消息 1
     * 报备失效消息 2
     * 报备即将失效消息 3
     * 成功报备消息 4
     *
     * @param mobile
     * @param customerName   客户姓名
     * @param customerMobile 客户手机
     * @param propertyName   楼盘名称
     * @param opt            消息类型
     * @return
     */
    void sendSmsMessage(String mobile, String customerName, String customerMobile, String propertyName, SmsMessageService.OptEnum opt);

    /**
     * 发送信息 通用方法
     * 到访消息 1
     * 报备失效消息 2
     * 报备即将失效消息 3
     * 成功报备消息 4
     *
     * @param smsList
     * @param opt
     */
    void sendMessage(List<ReportListDTO> smsList, SmsMessageService.OptEnum opt);

    /**
     * 发送报备成功消息
     *
     * @param smsList
     */
    void sendReportMessage(List<ReportListDTO> smsList);

    /**
     * 发送报备失效消息
     *
     * @param smsList
     */
    void sendInvalidMessage(List<ReportListDTO> smsList);

    /**
     * 发送报备即将失效消息
     *
     * @param smsList
     */
    void sendWillInvalidMessage(List<ReportListDTO> smsList);

    /**
     * 发送到访消息
     *
     * @param reportDTO
     */
    void sendInviteMessage(ReportDTO reportDTO);

    /**
     * 发送认证成功消息
     *
     * @param mobile
     */
    void sendAuthMessage(String mobile, String tel);

    /**
     * 发送楼盘推送
     *
     * @param mobileList
     * @param propertyDTO
     * @param tel
     */
    void sendPropertyMessage(List<String> mobileList, PropertyDTO propertyDTO, String tel);
}
