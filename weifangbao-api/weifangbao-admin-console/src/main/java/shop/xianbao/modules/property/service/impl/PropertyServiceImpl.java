package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.property.dao.PropertyDao;
import shop.xianbao.modules.property.dto.AreaParamDTO;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.entity.PropertyEntity;
import shop.xianbao.modules.property.enums.PropertyStatusEnum;
import shop.xianbao.modules.property.service.HelpFindHouseService;
import shop.xianbao.modules.property.service.PropertyRegionService;
import shop.xianbao.modules.property.service.PropertyService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Service
public class PropertyServiceImpl extends BaseServiceImpl<PropertyDao, PropertyEntity> implements PropertyService {

    @Autowired
    private PropertyRegionService propertyRegionService;

    @Autowired
    private HelpFindHouseService helpFindHouseService;

    @Resource
    private PropertyDao propertyDao;

    @Autowired
    private ApiSmsMessageService apiSmsMessageService;

    @Override
    public PageData<PropertyListDTO> page(Map<String, Object> params) {
        IPage page = getPage(params, null, false);
        List<PropertyListDTO> dtoList = baseDao.getList(params);
        return getPageData(dtoList, page.getTotal(), PropertyListDTO.class);
    }

    @Override
    public List<PropertyListDTO> list(Map<String, Object> params) {
        List<PropertyListDTO> dtoList = baseDao.getList(params);
        return dtoList;
    }

    @Override
    public List<PropertyDTO> comboList(Map<String, Object> params) {
        String id = (String)params.get("id");

        QueryWrapper<PropertyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        List<PropertyEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList, PropertyDTO.class);
    }

    @Override
    public PropertyDTO get(Long id) {
        PropertyDTO propertyDTO = propertyDao.get(id);
        return propertyDTO;
    }

    @Override
    public boolean add(PropertyDTO dto) {
        PropertyEntity entity = ConvertUtils.sourceToTarget(dto, PropertyEntity.class);
        return insert(entity);
    }

    @Override
    public boolean update(PropertyDTO dto) {
        PropertyEntity entity = ConvertUtils.sourceToTarget(dto, PropertyEntity.class);
        return updateById(entity);
    }

    @Override
    public Result check(PropertyDTO dto) {
        Result result = new Result();
        Long id = dto.getId();
        Integer propertyStatus = dto.getPropertyStatus();
        PropertyDTO propertyDTO = this.get(id);
        Integer oldStatus = propertyDTO.getPropertyStatus();
        if (oldStatus > (PropertyStatusEnum.UN_EXAMINE.getValue()) && !oldStatus.equals(PropertyStatusEnum.UN_PROVE.getValue())) {
            return result.error("请勿重复审核");
        }
        PropertyEntity newProperty = new PropertyEntity();
        newProperty.setId(id);
        // 审核通过直接上架
        if (propertyStatus.equals(PropertyStatusEnum.PROVE.getValue())) {
            propertyStatus = PropertyStatusEnum.ON_SALE.getValue();
        }
        newProperty.setPropertyStatus(propertyStatus);
        boolean update = updateById(newProperty);
        if (update) {
            if (propertyStatus.equals(PropertyStatusEnum.ON_SALE.getValue())) {
                // 上架楼盘
                // 推送新项目短信
                helpFindHouseService.sendPropertyMessage(propertyDTO);

            }
            return result.ok();
        } else {
            return result.error("操作失败");
        }
    }

    @Override
    public Result isTop(PropertyDTO dto) {
        Result result = new Result();
        Long id = dto.getId();
        Integer isTop = dto.getIsTop();
        PropertyEntity entity = new PropertyEntity();
        entity.setId(id);
        entity.setIsTop(isTop);
        boolean update = updateById(entity);
        if (update) {
            return result;
        }
        return result.error();
    }

    @Override
    public Result delete(Long[] ids) {
        UpdateWrapper<PropertyEntity> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", 1);
        wrapper.in("id", Arrays.asList(ids));
        boolean update = update(new PropertyEntity(), wrapper);
        if (update) {
            return new Result();
        }
        return new Result().error();
    }

    @Override
    public PageData<PropertyListDTO> userPage(Map<String, Object> params) {
        //单价
        Object unitPriceObj = params.get("unitPrice");
        if (unitPriceObj != null && StringUtils.isNotEmpty(unitPriceObj.toString())) {
            String unitPriceStr = unitPriceObj.toString();
            String[] unitPriceArr = unitPriceStr.split(",");
            params.put("minUnitPrice", unitPriceArr[0]);
            params.put("maxUnitPrice", unitPriceArr[1]);
        }
        //总价
        Object totalPriceObj = params.get("totalPrice");
        if (totalPriceObj != null && StringUtils.isNotEmpty(totalPriceObj.toString())) {
            String totalPriceStr = totalPriceObj.toString();
            String[] totalPriceArr = totalPriceStr.split(",");
            params.put("minTotalPrice", totalPriceArr[0]);
            params.put("maxTotalPrice", totalPriceArr[1]);
        }
        //户型
        Object apartmentObj = params.get("apartment");
        if (apartmentObj != null && StringUtils.isNotEmpty(apartmentObj.toString())) {
            params.put("apartment", apartmentObj.toString().split(","));
        }
        //面积
        Object areaObj = params.get("area");
        if (areaObj != null && StringUtils.isNotEmpty(areaObj.toString())) {
            String areaParamSQL = getAreaParamSQL(areaObj);
            params.put("area", areaParamSQL);
        }
        //类型
        Object typeObj = params.get("type");
        if (typeObj != null && StringUtils.isNotEmpty(typeObj.toString())) {
            params.put("type", typeObj.toString().split(","));
        }
        //售卖状态
        Object saleStatus = params.get("saleStatus");
        if (saleStatus != null && StringUtils.isNotEmpty(saleStatus.toString())) {
            params.put("saleStatus", saleStatus.toString().split(","));
        }
        //特色
        Object characteristic = params.get("characteristic");
        if (characteristic != null && StringUtils.isNotEmpty(characteristic.toString())) {
            params.put("characteristic", characteristic.toString().split(","));
        }
        //开盘时间
        Object openingDateObj = params.get("openingDate");
        if (openingDateObj != null && StringUtils.isNotEmpty(openingDateObj.toString())) {
            params.put("openingDate", openingDateObj.toString().split(","));
        }

        IPage page = getPage(params, "create_date", false);
        List<PropertyListDTO> dtoList = baseDao.getUserList(params);
        return getPageData(dtoList, page.getTotal(), PropertyListDTO.class);
    }

    @NotNull
    private static String getAreaParamSQL(Object areaObj) {
        StringBuilder areaParamSQL = new StringBuilder();
        List<AreaParamDTO> list = new ArrayList<>();
        String[] split = areaObj.toString().split(",");
        AreaParamDTO areaParamDTO = null;
        for (String s : split) {
            String[] sr = s.split("-");
            areaParamDTO = new AreaParamDTO();
            areaParamDTO.setMinArea(sr[0]);
            areaParamDTO.setMaxArea(sr.length > 1 ? sr[1] : "");
            list.add(areaParamDTO);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(e -> {
                areaParamSQL.append("NOT (");
                if (StringUtils.isNotBlank(e.getMinArea())) {
                    areaParamSQL.append("max_build_area <=" + e.getMinArea());
                }
                if (StringUtils.isNotBlank(e.getMinArea()) && StringUtils.isNotBlank(e.getMaxArea())) {
                    areaParamSQL.append(" OR min_build_area > " + e.getMaxArea());
                } else if (!StringUtils.isNotBlank(e.getMinArea()) && StringUtils.isNotBlank(e.getMaxArea())) {
                    areaParamSQL.append("min_build_area > " + e.getMaxArea());
                }
                areaParamSQL.append(") OR ");
            });
        }
        return areaParamSQL.substring(0, areaParamSQL.length() - 4);
    }
}