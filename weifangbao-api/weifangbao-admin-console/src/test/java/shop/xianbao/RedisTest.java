/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.modules.sys.entity.SysUserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        SysUserEntity user = new SysUserEntity();
        user.setEmail("123456@qq.com");
        redisUtils.set("user", user);

        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user")));
    }

}