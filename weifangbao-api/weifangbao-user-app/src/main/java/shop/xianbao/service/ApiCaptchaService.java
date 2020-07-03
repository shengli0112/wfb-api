/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service;

import shop.xianbao.common.service.CaptchaService;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 *
 * @author Tim tim@ruitukeji.com
 */
public interface ApiCaptchaService {
    /**
     * 生成 图像验证码
     */
    BufferedImage createIvImg(String ivId);

    /**
     * 图像验证码效验
     *
     * @param ivId   ivId
     * @param ivCode 验证码
     * @return true：成功  false：失败
     */
    boolean checkIvCode(String ivId, String ivCode);

    /**
     * 生成并返回 图像验证码
     */
    void responseIvImg(HttpServletResponse response, String ivid) throws IOException;

    boolean sendEmailCaptcha(String receiver, int opt);

    boolean sendSmsCaptcha(String mobile, int opt);

    /**
     * 校验验证码
     *
     * @param receiver
     * @param captcha
     * @param opt
     * @param isDle    是否删除验证码，true 删除，false 不删除
     * @return
     */
    boolean checkCaptcha(String receiver, String captcha, CaptchaService.OptEnum opt, Boolean isDle);
}

