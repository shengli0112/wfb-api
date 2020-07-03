/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.exception;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.xianbao.common.utils.HttpContextUtils;
import shop.xianbao.common.utils.IpUtils;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.log.entity.SysLogErrorEntity;
import shop.xianbao.modules.log.service.SysLogErrorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 异常处理器
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@RestControllerAdvice
public class RenExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RenExceptionHandler.class);

	@Autowired
	private SysLogErrorService sysLogErrorService;

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result handleMissingServletRequestParameterException(){
		return new Result().error(ErrorCode.PARAMS_GET_ERROR);
	}

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(XianbaoException.class)
	public Result handleRenException(XianbaoException ex){
		Result result = new Result();
		result.error(ex.getCode(), ex.getMsg());

		return result;
	}

	@ExceptionHandler(AuthorizationException.class)
	public Result handleDuplicateKeyException(AuthorizationException ex){
		Result result = new Result();
		result.error(ErrorCode.REQUEST_ACCESS_INVALID);

		return result;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
		Result result = new Result();
		result.error(ErrorCode.JSON_PARSING_FAILUREDELETE);

		return result;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException ex){
		Result result = new Result();
		result.error(ErrorCode.DB_RECORD_EXISTS);

		return result;
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex){
		logger.error(ex.getMessage(), ex);

		saveLog(ex);

		return new Result().error();
	}

	/**
	 * 保存异常日志
	 */
	private void saveLog(Exception ex){
		SysLogErrorEntity log = new SysLogErrorEntity();

		//请求相关信息
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		log.setIp(IpUtils.getIpAddr(request));
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setRequestUri(request.getRequestURI());
		log.setRequestMethod(request.getMethod());
		Map<String, String> params = HttpContextUtils.getParameterMap(request);
		if(MapUtil.isNotEmpty(params)){
			log.setRequestParams(JSON.toJSONString(params));
		}

		//异常信息
		log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

		//保存
		sysLogErrorService.save(log);
	}
}