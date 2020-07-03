/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
public
class PageParam implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer page       = 1;
    private Integer limitStart = 0;
    private Integer limit      = 20;
    private String  sortField  = "create_date";
    private String  sortBy     = "DESC";
    
}