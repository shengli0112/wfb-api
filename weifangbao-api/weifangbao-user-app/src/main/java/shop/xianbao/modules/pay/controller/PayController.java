package shop.xianbao.modules.pay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.xianbao.annotation.Login;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.pay.DTO.PayParamDTO;
import shop.xianbao.modules.pay.service.PayService;

import java.util.Objects;

/**
 * @program: PayController
 * @description: 支付接口
 * @author: yh
 * @create: 2019-06-05 14:01
 **/
@Api("聚合支付Api")
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/toPay", method = RequestMethod.POST)
    public Result toPay(@RequestBody PayParamDTO payParamDTO) {
        if (StringUtils.isBlank(payParamDTO.getPayCode())) {
            return new Result().error("[payCode]必传");
        }
        if (null == payParamDTO.getOrderId()) {
            return new Result().error("[orderId]必传");
        }

        Object result = payService.orderPay(payParamDTO);
        if (!Objects.equals(null, result)) {
            return new Result().ok(result);
        } else {
            return new Result().error("订单不存在");
        }

    }

    @PostMapping("/group")
    @ApiOperation("拼团支付")
    public Result pay(@RequestBody PayParamDTO payParamDTO) {
        Long orderId = payParamDTO.getOrderId();
        if (Objects.equals(null, orderId)) {
            return new Result().error("orderId不能为空");
        }
        Object result = payService.groupPay(payParamDTO);
        System.out.println(result);
        if (!Objects.equals(null, result)) {
            return new Result().ok(result);
        } else {
            return new Result().error("订单不存在");
        }
    }

    @Login
    @GetMapping("/getWxConfig")
    @ApiOperation("获取微信配置信息")
    public Result getWxConfig(@RequestParam String returnUrl) throws WxErrorException {
        return payService.getWxConfig(returnUrl);
    }
}
