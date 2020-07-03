package shop.xianbao.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.PinYinUtils;
import shop.xianbao.modules.customer.dao.CustomerDao;
import shop.xianbao.modules.customer.dto.CustomerDTO;
import shop.xianbao.modules.customer.dto.CustomerListDTO;
import shop.xianbao.modules.customer.entity.CustomerEntity;
import shop.xianbao.modules.customer.service.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 客户表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-12
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {
    @Override
    public PageData<CustomerListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, null, true);
        List<CustomerListDTO> dtoList = baseDao.getList(params);
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.forEach(c -> c.setFullName(c.getSurname() + c.getName()));
        }
        return getPageData(dtoList, page.getTotal(), CustomerListDTO.class);
    }

    @Override
    public List<CustomerListDTO> list(Map<String, Object> params) {
        List<CustomerListDTO> dtoList = baseDao.getList(params);
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.forEach(c -> c.setFullName(c.getSurname() + c.getName()));
        }
        return dtoList;
    }

    @Override
    public List<CustomerDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<CustomerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<CustomerEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, CustomerDTO.class);
    }

    @Override
    public CustomerDTO get(Long id) {
        CustomerEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, CustomerDTO.class);
    }

    @Override
    public boolean add(CustomerDTO dto, Long unionId) {
        CustomerEntity entity = ConvertUtils.sourceToTarget(dto, CustomerEntity.class);
        entity.setUnionId(unionId);
        entity.setFirstSpelling(PinYinUtils.getFirstLettersUp(StringUtils.join(entity.getSurname(), entity.getName())));
        return insert(entity);
    }

    @Override
    public boolean update(CustomerDTO dto) {
        CustomerEntity entity = ConvertUtils.sourceToTarget(dto, CustomerEntity.class);
        entity.setFirstSpelling(PinYinUtils.getFirstLettersUp(StringUtils.join(entity.getSurname(), entity.getName())));
        return updateById(entity);
    }

    @Override
    public int delete(Long[] ids) {
        UpdateWrapper<CustomerEntity> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", 1);
        wrapper.in("id", Arrays.asList(ids));
        return baseDao.update(new CustomerEntity(), wrapper);
    }
}