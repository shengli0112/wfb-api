/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 *
 * @author Tim tim@ruitukeji.com
 */
public interface CaptchaService {

    //身份验证验证码 SMS_108400070
    //登录确认验证码 SMS_108400068
    //登录异常验证码 SMS_108400067
    //用户注册验证码 SMS_108400066
    //修改密码验证码 SMS_108400065
    //信息变更验证 SMS_108400064

    enum OptEnum {
        /**
         * 注册 todo 模板可配置
         */
        REGISTER(1, "SMS_108400066"),
        /**
         * 登录 todo 模板可配置
         */
        LOGIN(2, "SMS_108400068"),
        /**
         * 找回密码 todo 模板可配置
         */
        RESET_PWD(3, "SMS_108400065"),
        /**
         * 修改敏感信息 todo 模板可配置
         */
        CHANGE_INFO(4, "SMS_108400064"),
        /**
         * 修改手机号 todo 模板可配置
         */
        CHANGE_MOBILE(5, "SMS_108400070"),
        /**
         * 绑定手机号 todo 模板可配置
         */
        BIND_MOBILE(6, "SMS_108400064"),
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
     * 生成 图像验证码
     */
    BufferedImage createIvImg(String uuid);

    /**
     * 图像验证码效验
     *
     * @param ivId   uuid
     * @param ivCode 验证码
     * @return true：成功  false：失败
     */
    boolean checkIvCode(String ivId, String ivCode);

    /**
     * 生成并返回 图像验证码
     */
    void responseIvImg(HttpServletResponse response, String ivid) throws IOException;

    /**
     * 发送短信验证码
     */
    boolean sendSmsCaptcha(String mobile, OptEnum opt);

    /**
     * 发送邮件验证码
     */
    boolean sendEmailCaptcha(String receiver, OptEnum opt);

    /**
     * 校验验证码
     *
     * @param receiver
     * @param captcha
     * @param opt
     * @param isDle    是否删除验证码，true 删除，false 不删除
     * @return
     */
    boolean checkCaptcha(String receiver, String captcha, OptEnum opt, Boolean isDle);
}
