/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.sys.service;

import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author Tim tim@ruitukeji.com
 */
public interface SysCaptchaService {
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

}

