/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.utils;

/**
 * 自定义异常
 * 
 * @author Tim tim@ruitukeji.com
 */
public class RenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public RenException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public RenException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public RenException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public RenException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
