package shop.xianbao.modules.member.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.member.dto.MemberAddressDTO;
import shop.xianbao.modules.member.dto.MemberAddressListDTO;
import shop.xianbao.modules.member.entity.MemberAddressEntity;

/**
 * 用户收货地址表
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
public interface MemberAddressService extends XianbaoCrudService<MemberAddressEntity, MemberAddressDTO, MemberAddressListDTO> {

    /**
     * 获取收货地址
     *
     * @param unionId
     * @param addressId
     * @return
     */
    MemberAddressListDTO getMemberAddressDTO(Long unionId, Long addressId);
}