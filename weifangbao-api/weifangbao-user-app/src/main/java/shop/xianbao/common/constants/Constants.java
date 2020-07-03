package shop.xianbao.common.constants;

/**
 * 常量
 *
 */
public class Constants {

    /**
     * 密码盐值长度
     */
    public static final int PASSWORD_SALT_SIZE = 6;

    /**
     * 手机号码正则
     */
    public static final String MobilePattern = "^(0|86|17951)?(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$";

    public static final String IdNumber =
        "^([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2})$";

    /**
     * 经度正则表达式
     */
    public static final String LongitudePattern = "^(-|\\+)?(180\\.0{4,10}|(\\d{1,2}|1[0-7]\\d)\\.\\d{4,10})$";

    /**
     * 纬度正则表达式
     */
    public static final String LatitudePattern = "^(-|\\+)?(90\\.0{4,10}|(\\d|[1-8]\\d)\\.\\d{4,10})$";

    /**
     * 地址正则表达式
     */
    public static final String URIPattern = "^(https?|ftp|file)://[-a-zA-Z0-9@#/%?=~_|!:,.;]*[-a-zA-Z0-9@#/%=~_|]";

    /**
     * 密码正则表达式
     */
    public static final String PasswordPattern = "^.{6,12}$";

    /**
     * 默认页码
     */
    public static final Integer DefaultPage = 1;

    /**
     * 默认每页记录数
     */
    public static final Integer DefaultPageSize = 10;

}
