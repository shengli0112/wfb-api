/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.service;

import shop.xianbao.common.entity.SysSmsEntity;
import shop.xianbao.common.service.BaseService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * http
 *
 */
public
interface HttpService {
    void downloadFile(File file, HttpServletResponse response) throws IOException;
}
