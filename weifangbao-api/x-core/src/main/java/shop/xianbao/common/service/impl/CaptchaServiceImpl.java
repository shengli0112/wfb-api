/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.service.impl;

import com.google.code.kaptcha.Producer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.redis.RedisKeys;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.common.service.CaptchaService;
import shop.xianbao.common.sms.AbstractSmsService;
import shop.xianbao.common.sms.SmsFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 *
 * @author Tim tim@ruitukeji.com
 */
public abstract class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${renren.redis.open}")
    private boolean open;

    /**
     * Local Cache  5分钟过期
     */
    Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();

    public OptEnum getOptEnum(int key) {
        for (OptEnum em : OptEnum.values()) {
            if (em.key() == key) {
                return em;
            }
        }
        return null;
    }

    @Override
    public BufferedImage createIvImg(String ivid) {
        //生成文字验证码
        String code = producer.createText();

        //保存到缓存
        setCache(ivid, code);

        return producer.createImage(code);
    }

    @Override
    public void responseIvImg(HttpServletResponse response, String ivid) throws IOException {
        //生成图片验证码
        BufferedImage image = createIvImg(ivid);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = null;

        out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();

    }

    @Override
    public boolean checkIvCode(String ivid, String ivcode) {
        //获取验证码
        String captcha = getCache(ivid, true);

        //效验成功
        return ivcode.equalsIgnoreCase(captcha);
    }

    /**
     * 发送短信验证码
     *
     * @param receiver
     * @param opt
     * @return
     */
    @Override
    public boolean sendSmsCaptcha(String receiver, OptEnum opt) {
        if (opt == null) {
            return false;
        }
        //短信服务
        AbstractSmsService service = SmsFactory.build();
        if (service == null) {
            throw new XianbaoException(ErrorCode.SMS_CONFIG);
        }

        LinkedHashMap<String, String> params = Maps.newLinkedHashMap();
        String captchaCode = RandomStringUtils.randomNumeric(4);
        params.put("code", captchaCode);

        //发送短信
        service.sendSms(receiver, params, opt.template());
        setCache(getCacheKey(receiver, opt.key()), captchaCode);
        return true;
    }

    public boolean sendSmsCaptcha(String mobile, int opt) {

        return sendSmsCaptcha(mobile, getOptEnum(opt));
    }

    /**
     * todo
     *
     * @param receiver
     * @param opt
     * @return
     */
    @Override
    public boolean sendEmailCaptcha(String receiver, OptEnum opt) {
        return false;
    }

    public boolean sendEmailCaptcha(String mobile, int opt) {
        return sendEmailCaptcha(mobile, getOptEnum(opt));

    }

    /**
     * 校验验证码
     *
     * @param receiver
     * @param captcha
     * @param opt
     * @param isDle    是否删除验证码，true 删除，false 不删除
     * @return
     */
    @Override
    public boolean checkCaptcha(String receiver, String captcha, OptEnum opt, Boolean isDle) {
        String okCaptcha = getCache(getCacheKey(receiver, opt.key()), isDle);
        if (StringUtils.isAnyBlank(receiver, captcha, okCaptcha)) {
            return false;
        }
        return Objects.equals(okCaptcha, captcha);
    }

    private void setCache(String key, String value) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 300);
        } else {
            localCache.put(key, value);
        }
    }

    private String getCacheKey(String receiver, int opt) {
        return receiver + opt;
    }

    /**
     * @param key
     * @param isDle 是否删除验证码，true 删除，false 不删除
     * @return
     */
    private String getCache(String key, Boolean isDle) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            String captcha = (String)redisUtils.get(key);
            //删除验证码
            if (captcha != null && isDle) {
                redisUtils.delete(key);
            }

            return captcha;
        }

        String captcha = localCache.getIfPresent(key);
        //删除验证码
        if (captcha != null && isDle) {
            localCache.invalidate(key);
        }
        return captcha;
    }
}