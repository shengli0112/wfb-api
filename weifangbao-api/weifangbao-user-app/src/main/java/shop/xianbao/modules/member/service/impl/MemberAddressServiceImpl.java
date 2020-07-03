package shop.xianbao.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.MemberAddressDao;
import shop.xianbao.modules.member.dto.MemberAddressDTO;
import shop.xianbao.modules.member.dto.MemberAddressListDTO;
import shop.xianbao.modules.member.entity.MemberAddressEntity;
import shop.xianbao.modules.member.service.MemberAddressService;
import shop.xianbao.modules.setting.service.AreaService;

import java.util.List;
import java.util.Map;

/**
 * 用户收货地址表
 *
 * @author yanghuan
 * @since 1.0.0 2019-03-07
 */
@Service
public class MemberAddressServiceImpl extends XianbaoCrudServiceImpl<MemberAddressDao, MemberAddressEntity, MemberAddressDTO, MemberAddressListDTO> implements MemberAddressService {
    @Autowired
    private AreaService areaService;

    @Override
    public QueryWrapper<MemberAddressEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<MemberAddressEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("union_id", params.get("union_id").toString());
        wrapper.eq("is_deleted", 0);
        wrapper.orderByDesc("is_default");
        wrapper.orderByDesc("create_date");
        return wrapper;
    }

    @Override
    public MemberAddressListDTO getMemberAddressDTO(Long unionId, Long addressId) {
        MemberAddressListDTO memberAddressListDTO = null;
        QueryWrapper<MemberAddressEntity> addressWrapper = new QueryWrapper<>();
        addressWrapper.eq("union_id", unionId);
        if (addressId != null) {
            addressWrapper.eq("id", addressId);
        }
        addressWrapper.eq("is_deleted", 0);
        addressWrapper.orderByDesc("is_default");
        addressWrapper.orderByDesc("create_date");
        List<MemberAddressEntity> addressEntityList = this.selectList(addressWrapper);
        if (addressEntityList != null && addressEntityList.size() > 0) {
            memberAddressListDTO = ConvertUtils.sourceToTarget(addressEntityList.get(0), MemberAddressListDTO.class);
            memberAddressListDTO.setLocation(areaService.get(memberAddressListDTO.getDistrict()).getMergerName().replace(",", "") + memberAddressListDTO.getAddress());
        }
        return memberAddressListDTO;
    }
}