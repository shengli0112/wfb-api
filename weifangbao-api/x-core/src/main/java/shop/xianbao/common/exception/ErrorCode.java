/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.exception;

/**
 * @author Tim tim@ruitukeji.com
 * @since 1.0.0
 */
public
interface ErrorCode{
    int INTERNAL_SERVER_ERROR = 500;
    int UNAUTHORIZED          = 401;
    int FORBIDDEN             = 403;
    
    //# 400x 客户端请求异常
    //4000=非法请求。{0}
    int REQUEST_ILLEGAL              = 4000;
    //4001=请求缺少参数{0}
    int REQUEST_PARAM_MISSING        = 4001;
    //4002=请求参数格式错误{0}
    int REQUEST_PARAM_FORMAT_ILLEGAL = 4002;
    //4003=非法参数{0}
    int REQUEST_PARAM_ILLEGAL        = 4003;
    //4004=暂无数据
    int REQUEST_NO_DATA              = 4004;
    //# 401x 鉴权失败
    //4010=无权访问。{0}
    int REQUEST_ACCESS_INVALID       = 4010;
    //4011=token丢失
    int REQUEST_ACCESS_NO_TOKEN      = 4011;
    //4012=token无效
    int REQUEST_ACCESS_TOKEN_INVALID = 4012;
    //4013=token过期
    int REQUEST_ACCESS_TOKEN_EXPIRED = 4013;
    //4014=账号或密码错误
    int REQUEST_ACCESS_PWD_WRONG     = 4014;
    //4015=签名校验失败
    int REQUEST_ACCESS_SIGN_WRONG    = 4015;
    //4016=验证码错误
    int REQUEST_ACCESS_CAPTCHA_WRONG = 4016;
    //4017=用户未注册
    int REQUEST_ACCESS_UNREG         = 4017;
    
    //#50xx 服务端端异常
    //5000=服务端异常。{0}
    int SERVER_ERROR          = 5000;
    //5020=数据库操作异常。{0}
    int SERVER_DB_EXCEPTION   = 5020;
    //5030=文件操作异常。{0}
    int SERVER_FILE_EXCEPTION = 5030;
    
    //#60xx 调用第三方接口异常
    int THIRD_ERROR          = 6000;
    //6001=调用第三方接口无法访问。{0}
    int THIRD_CALL_FAIL      = 6001;
    //6002=调用第三方接口返回异常。{0}
    int THIRD_CALL_EXCEPTION = 6002;
    
    
    int NOT_NULL                   = 10001;
    int DB_RECORD_EXISTS           = 10002;
    int PARAMS_GET_ERROR           = 10003;
    int ACCOUNT_PASSWORD_ERROR     = 10004;
    int ACCOUNT_DISABLE            = 10005;
    int IDENTIFIER_NOT_NULL        = 10006;
    int CAPTCHA_ERROR              = 10007;
    int SUB_MENU_EXIST             = 10008;
    int PASSWORD_ERROR             = 10009;
    int ACCOUNT_NOT_EXIST          = 10010;
    int SUPERIOR_DEPT_ERROR        = 10011;
    int SUPERIOR_MENU_ERROR        = 10012;
    int DATA_SCOPE_PARAMS_ERROR    = 10013;
    int DEPT_SUB_DELETE_ERROR      = 10014;
    int DEPT_USER_DELETE_ERROR     = 10015;
    int ACT_DEPLOY_ERROR           = 10016;
    int ACT_MODEL_IMG_ERROR        = 10017;
    int ACT_MODEL_EXPORT_ERROR     = 10018;
    int UPLOAD_FILE_EMPTY          = 10019;
    int TOKEN_NOT_EMPTY            = 10020;
    int TOKEN_INVALID              = 10021;
    int ACCOUNT_LOCK               = 10022;
    int ACT_DEPLOY_FORMAT_ERROR    = 10023;
    int OSS_UPLOAD_FILE_ERROR      = 10024;
    int SEND_SMS_ERROR             = 10025;
    int MAIL_TEMPLATE_NOT_EXISTS   = 10026;
    int REDIS_ERROR                = 10027;
    int JOB_ERROR                  = 10028;
    int INVALID_SYMBOL             = 10029;
    int JSON_FORMAT_ERROR          = 10030;
    int SMS_CONFIG                 = 10031;
    int PERMISSION_NOT_EXIST       = 10032;
    int MOBILE_FORMAT_ERROR        = 10033;
    int DB_RECORD_NOT_EXISTS       = 10034;
    int DB_RECORD_OPERATION_FAILED = 10037;
    int DEDUCTION_DATA_LARGER      = 10038;
    int LEVEL_NOT_DELETE           = 10039;
    int JSON_PARSING_FAILUREDELETE = 10040;
    int CHILD_RECORD_EXISTS        = 10041;
    int URL_ERROR                  = 10042;
    int DATA_CAN_NOT_DELETE        = 10043;
    int NO_PRINTER                 = 10044;
}
