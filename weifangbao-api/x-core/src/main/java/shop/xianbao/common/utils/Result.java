/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;

import java.io.Serializable;

/**
 * 响应数据
 *
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
@ApiModel(value = "响应")
public
class Result<T> implements Serializable{
    private static final long   serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    @ApiModelProperty(value = "编码：0表示成功，其他值表示失败")
    private              int    code             = 0;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private              String msg              = "success";
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private              T      data;
    
    public
    Result<T> ok(){
        return this;
    }
    
    public
    Result<T> ok(T data){
        this.setData(data);
        return this;
    }
    
    public
    boolean success(){
        return code == 0 ? true : false;
    }
    
    public
    Result<T> error(XianbaoException ex){
        if(ex == null){
            return error();
        }
        this.code = ex.getCode();
        this.msg = ex.getMsg();
        return this;
    }
    
    public
    Result<T> error(){
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }
    
    public
    Result<T> error(int code){
        this.code = code;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }
    
    public
    Result<T> error(int code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
    
    public
    Result<T> error(String msg){
        this.code = ErrorCode.REQUEST_ILLEGAL;
        this.msg = msg;
        return this;
    }
    
    public
    int getCode(){
        return code;
    }
    
    public
    void setCode(int code){
        this.code = code;
    }
    
    public
    String getMsg(){
        return msg;
    }
    
    public
    void setMsg(String msg){
        this.msg = msg;
    }
    
    public
    T getData(){
        return data;
    }
    
    public
    void setData(T data){
        this.data = data;
    }
}
