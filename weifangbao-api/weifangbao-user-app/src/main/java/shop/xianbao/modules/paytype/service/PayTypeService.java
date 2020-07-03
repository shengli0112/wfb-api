package shop.xianbao.modules.paytype.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.paytype.dto.PayTypeDTO;
import shop.xianbao.modules.paytype.dto.PayTypeListDTO;
import shop.xianbao.modules.paytype.dto.PayTypeRealNameListDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;

import java.util.List;

/**
 * 支付方式
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
public interface PayTypeService extends XianbaoCrudService<PayTypeEntity, PayTypeDTO, PayTypeListDTO> {

    /**
     * 获取支付列表
     *
     * @param user
     * @return
     */
    List<PayTypeRealNameListDTO> payTypeList(MemberDataEntity user);

    /**
     * 根据payCode获取支付方式
     *
     * @param payCode
     * @param tradeType
     * @return
     */
    PayTypeEntity getPayTypeByCode(String payCode, String tradeType);

    /**
     * 根据id获取支付方式
     *
     * @param id
     * @return
     */
    PayTypeEntity getPayTypeById(Long id);

}