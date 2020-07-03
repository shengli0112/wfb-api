package shop.xianbao.modules.pay.strategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.modules.pay.dto.RefundParamDTO;
import shop.xianbao.modules.pay.utils.SpringUtils;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;

/**
 * @program: PayContextStrategy
 * @description: 上下文
 * @author: yh
 * @create: 2019-06-05 10:16
 **/
@Component
public class PayContextStrategy {

    @Autowired
    private PayTypeService payTypeService;

    @Autowired
    private SpringUtils springUtils;

    public <T> T refund(RefundParamDTO refundParamDTO) {
        PayTypeEntity payTypeEntity = payTypeService.getPayTypeById(refundParamDTO.getPayTypeId());
        PayStrategy payStrategy = getPayStrategy(payTypeEntity);
        return payStrategy.refund(payTypeEntity, refundParamDTO);
    }

    private PayStrategy getPayStrategy(PayTypeEntity payTypeEntity) {
        //1.使用payCode 获取beanId
        if (null == payTypeEntity) {
            throw new XianbaoException("不支持该支付方式");
        }
        if (payTypeEntity.getStatus() != 1) {
            throw new XianbaoException("该支付方式已停用");
        }
        //2.获取beanId
        String beanId = payTypeEntity.getStrategyBeanId();
        if (StringUtils.isBlank(beanId)) {
            throw new XianbaoException("该支付方式未配置beanId");
        }
        String config = payTypeEntity.getConfig();
        if (StringUtils.isBlank(config)) {
            throw new XianbaoException("该支付方式未配置参数");
        }
        //3.使用spring容器获取实例对象
        return springUtils.getBean(beanId, PayStrategy.class);
    }
}
