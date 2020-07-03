/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.page;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import shop.xianbao.common.constant.Constant;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * 分页工具类
 *
 * @author Tim tim@ruitukeji.com
 */
public
class PageUtil implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static
    PageParam getPageParam(Map<String, Object> params){
        PageParam page = new PageParam();
        if(params == null){
            params = Maps.newHashMap();
        }
        Integer pageIndex = MapUtil.getInt(params, Constant.PAGE);
        Integer pageLimit = MapUtil.getInt(params, Constant.LIMIT);
        String  sortField = MapUtil.getStr(params, Constant.ORDER_FIELD);
        page.setPage(pageIndex == null || pageIndex<1 ? page.getPage() : pageIndex);
        page.setLimit(pageLimit == null || pageLimit<0 ? page.getLimit() : pageLimit);
        page.setLimitStart(page.getLimit()*(page.getPage()-1));
        page.setSortField(StringUtils.isBlank(sortField) ? page.getSortField() : sortField);
        page.setSortBy(Objects.equals(params.get(Constant.ORDER), Constant.ASC) ? "ASC" : "DESC");
        
        params.put("page", page.getPage());
        params.put("limitStart", page.getLimitStart());
        params.put("limit", page.getLimit());
        params.put("sortField", page.getSortField());
        params.put("sortBy", page.getSortBy());
        
        return page;
    }
    
    
}