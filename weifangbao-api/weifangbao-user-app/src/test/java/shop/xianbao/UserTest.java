/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.xianbao.modules.member.dto.CheckCaptchaDTO;
import shop.xianbao.modules.member.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void test1() {
        CheckCaptchaDTO checkCaptchaDTO = new CheckCaptchaDTO();
//        checkCaptchaDTO.setOldMobile("15735294568");
        checkCaptchaDTO.setNewMobile("15735294568");
    }

}