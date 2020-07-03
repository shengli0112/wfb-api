package shop.xianbao.common.service;

import java.util.LinkedHashMap;

/**
 * @program: MessageService
 * @description:
 * @author: yh
 * @create: 2019-11-28 17:24
 **/
public interface SmsMessageService {
    enum OptEnum {
        /**
         * 到访模板
         */
        INVITE(1, "201097"),
        /**
         * 报备失效
         */
        REPORT_INVALID(2, "201091"),
        /**
         * 报备2小时即将失效
         */
        REPORT_WILL_INVALID(3, "201089"),
        /**
         * 成功报备
         */
        REPORT(4, "201088"),
        /**
         * 新进项目
         */
        NEW_PROPERTY(5, "201086"), // 【微房宝】微房宝提醒您，微房宝#project#，#area#,#price#，#commission#，#param#；#tel#
        /**
         * 认证
         */
        ATUH(6, "201085"),

        REPORT_TO_MNG(7, "211536"),
        ;

        private int key;

        private String template;

        OptEnum(int key, String value) {
            this.key = key;
            this.template = value;
        }

        public int key() {
            return this.key;
        }

        public String template() {
            return this.template;
        }
    }

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param params 模板参数
     * @param opt    模板
     * @return
     */
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
     * 新上楼盘推送消息
     *
     * @param mobile
     * @param propertyName
     * @param area
     * @param price
     * @param commission
     * @param tel
     */
    void sendSmsMessage(String mobile, String propertyName, String area, String price, String commission,String param, String tel, SmsMessageService.OptEnum opt);
}
