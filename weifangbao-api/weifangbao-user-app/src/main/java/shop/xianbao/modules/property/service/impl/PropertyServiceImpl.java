package shop.xianbao.modules.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import shop.xianbao.modules.member.entity.MemberDataEntity;
import shop.xianbao.modules.property.dao.PropertyDao;
import shop.xianbao.modules.property.dto.AreaParamDTO;
import shop.xianbao.modules.property.dto.PropertyCollectDTO;
import shop.xianbao.modules.property.dto.PropertyDTO;
import shop.xianbao.modules.property.dto.PropertyListDTO;
import shop.xianbao.modules.property.entity.PropertyEntity;
import shop.xianbao.modules.property.enums.PropertyStatusEnum;
import shop.xianbao.modules.property.service.HelpFindHouseService;
import shop.xianbao.modules.property.service.PropertyCollectService;
import shop.xianbao.modules.property.service.PropertyRegionService;
import shop.xianbao.modules.property.service.PropertyService;
import shop.xianbao.modules.sys.service.SysParamsService;

import javax.annotation.Resource;
import java.util.*;

/**
 * 楼盘表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-13
 */
@Service
public class PropertyServiceImpl extends BaseServiceImpl<PropertyDao, PropertyEntity> implements PropertyService {
    @Resource
    private PropertyDao propertyDao;

    @Autowired
    private PropertyCollectService propertyCollectService;

    @Autowired
    private PropertyRegionService propertyRegionService;

    @Autowired
    private HelpFindHouseService helpFindHouseService;

    @Autowired
    private SysParamsService sysParamsService;

    @Override
    public PageData<PropertyListDTO> page(Map<String, Object> params, MemberDataEntity user) {
        params.put("unionId", user.getUnionId());
        IPage page = getPage(params, null, false);
        List<PropertyListDTO> dtoList = baseDao.getList(params);
        handleProperty(user, dtoList);
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
    public PropertyDTO get(Long id, MemberDataEntity user) {
        PropertyDTO propertyDTO = propertyDao.get(id);
        if (propertyDTO == null) {
            return null;
        }
        if (Objects.equals(null, user) || user.getIsRealAuth() != 1) {
            propertyDTO.setCommission(null);
        }
        propertyDTO.setIsCollect(0);
        if (!Objects.equals(null, user)) {
            PropertyCollectDTO collect = propertyCollectService.getCollect(propertyDTO.getId(), user.getUnionId());
            if (!Objects.equals(null, collect)) {
                propertyDTO.setIsCollect(1);
            }
        }
        if (propertyDTO.getIsTop().equals(1)) {
            propertyDTO.setTopLabel(sysParamsService.getValue("TOP_LABEL"));
        }
        propertyDTO.setAreaName(propertyRegionService.getAreaNameById(propertyDTO.getAreaId()));
        return propertyDTO;
    }

    @Override
    public Result add(PropertyDTO dto, MemberDataEntity user) {
        Result result = new Result();
        PropertyEntity entity = ConvertUtils.sourceToTarget(dto, PropertyEntity.class);
        entity.setUnionId(user.getUnionId());
        insert(entity);
        return result.ok();
    }

    @Override
    public Result update(PropertyDTO dto, MemberDataEntity user) {
        Result result = new Result();
        PropertyDTO propertyDTO = this.get(dto.getId(), user);
        Integer propertyStatus = propertyDTO.getPropertyStatus();
        if (propertyStatus.equals(PropertyStatusEnum.UN_EXAMINE.getValue())) {
            return result.error("审核中，无法修改");
        }
        PropertyEntity entity = ConvertUtils.sourceToTarget(dto, PropertyEntity.class);
        entity.setPropertyStatus(1);
        boolean b = updateById(entity);
        if (b) {
            return result.ok();
        } else {
            return result.error("操作失败");
        }
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public Result updateStatus(PropertyDTO dto, MemberDataEntity user) {
        Result result = new Result();
        Long id = dto.getId();
        Integer propertyStatus = dto.getPropertyStatus();
        PropertyDTO propertyDTO = this.get(id, user);
        Integer oldStatus = propertyDTO.getPropertyStatus();
        if (!propertyStatus.equals(PropertyStatusEnum.ON_SALE.getValue()) && !propertyStatus.equals(PropertyStatusEnum.UN_SALE.getValue())) {
            return result.error("propertyStatus值非法");
        }
        if (oldStatus <= (PropertyStatusEnum.UN_EXAMINE.getValue()) || oldStatus.equals(PropertyStatusEnum.UN_PROVE.getValue())) {
            return result.error("未通过审核");
        }
        PropertyEntity newProperty = new PropertyEntity();
        newProperty.setId(id);
        newProperty.setPropertyStatus(propertyStatus);
        boolean update = updateById(newProperty);
        if (update) {
            if (propertyStatus.equals(PropertyStatusEnum.ON_SALE.getValue())) {
                // 上架楼盘
                helpFindHouseService.helpFindHouse(propertyDTO);
            }
            return result.ok();
        } else {
            return result.error("操作失败");
        }
    }

    @Override
    public PageData<PropertyListDTO> userPage(Map<String, Object> params, MemberDataEntity user) {
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
        //mark
        Object markDateObj = params.get("mark");
        if (markDateObj != null && StringUtils.isNotEmpty(markDateObj.toString())) {
            params.put("is_"+markDateObj.toString(),1);
        }
        Object orderByObj = params.get("orderBy");
        Object orderSortObj = params.get("orderSort");

        if (Objects.equals(null, orderByObj) && Objects.equals(null, orderSortObj)) {
            params.put("orderBy", "update_date");
            params.put("orderSort", "DESC");
        }

        IPage page = getPage(params, null, false);
        List<PropertyListDTO> dtoList = baseDao.getUserList(params);
        handleProperty(user, dtoList);
        return getPageData(dtoList, page.getTotal(), PropertyListDTO.class);
    }

    private void handleProperty(MemberDataEntity user, List<PropertyListDTO> dtoList) {
        if (CollectionUtils.isNotEmpty(dtoList)) {
            if (Objects.equals(null, user) || user.getIsRealAuth() != 1) {
                dtoList.forEach(e -> e.setCommission(null));
            }
            handlePropertyTopLabel(dtoList);
        }
    }

    private void handlePropertyTopLabel(List<PropertyListDTO> dtoList) {
        if (CollectionUtils.isNotEmpty(dtoList)) {
            String topLabel = sysParamsService.getValue("TOP_LABEL");
            dtoList.stream().filter(e -> e.getIsTop().equals(1)).forEach(e -> e.setTopLabel(topLabel));
        }
    }

    @Override
    public List<PropertyListDTO> hotList(Map<String, Object> params, MemberDataEntity user) {
        params.put("isTop", 1);
        params.put("limitNum", 5);
        List<PropertyListDTO> list = baseDao.getUserList(params);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        handleProperty(user, list);
        return list;
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
                    areaParamSQL.append("p.max_build_area <=" + e.getMinArea());
                }
                if (StringUtils.isNotBlank(e.getMinArea()) && StringUtils.isNotBlank(e.getMaxArea())) {
                    areaParamSQL.append(" OR p.min_build_area > " + e.getMaxArea());
                } else if (!StringUtils.isNotBlank(e.getMinArea()) && StringUtils.isNotBlank(e.getMaxArea())) {
                    areaParamSQL.append("p.min_build_area > " + e.getMaxArea());
                }
                areaParamSQL.append(") OR ");
            });
        }
        return areaParamSQL.substring(0, areaParamSQL.length() - 4);
    }
}