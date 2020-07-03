package shop.xianbao.modules.paytype.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.member.dao.UserAuthenticationDao;
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.paytype.dao.PayTypeDao;
import shop.xianbao.modules.paytype.dto.PayTypeDTO;
import shop.xianbao.modules.paytype.dto.PayTypeListDTO;
import shop.xianbao.modules.paytype.dto.PayTypeRealNameListDTO;
import shop.xianbao.modules.paytype.entity.PayTypeEntity;
import shop.xianbao.modules.paytype.service.PayTypeService;
import shop.xianbao.modules.unionuser.entity.UserAuthenticationEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 支付方式
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-09
 */
@Service
public class PayTypeServiceImpl extends XianbaoCrudServiceImpl<PayTypeDao, PayTypeEntity, PayTypeDTO, PayTypeListDTO> implements PayTypeService {

    @Resource
    private UserAuthenticationDao userAuthenticationDao;

    @Override
    public QueryWrapper<PayTypeEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<PayTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public List<PayTypeRealNameListDTO> payTypeList(MemberDataEntity user) {
        QueryWrapper<PayTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<PayTypeEntity> list = selectList(queryWrapper);
        List<PayTypeRealNameListDTO> payTypeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            payTypeList = ConvertUtils.sourceToTarget(list, PayTypeRealNameListDTO.class);
            QueryWrapper<UserAuthenticationEntity> wrapper = new QueryWrapper();
            wrapper.eq("union_id", user.getUnionId());
            UserAuthenticationEntity entity = userAuthenticationDao.selectOne(wrapper);
            if (entity != null) {
                payTypeList.stream().forEach(e -> e.setStatus(entity.getStatus()));
            } else {
                payTypeList.stream().forEach(e -> e.setStatus(0));
            }
        }
        return payTypeList;
    }

    @Override
    public PayTypeEntity getPayTypeByCode(String payCode, String tradeType) {
        QueryWrapper<PayTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pay_code", payCode);
        queryWrapper.eq("trade_type", tradeType);
        queryWrapper.eq("is_deleted", 0);
        List<PayTypeEntity> list = this.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public PayTypeEntity getPayTypeById(Long id) {
        return selectById(id);
    }
}