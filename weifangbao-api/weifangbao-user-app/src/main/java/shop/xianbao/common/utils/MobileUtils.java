package shop.xianbao.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @program: MobileUtils
 * @description: 手机号工具类
 * @author: yh
 * @create: 2019-12-09 15:59
 **/
public class MobileUtils {
    public static String secret(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return "";
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static boolean validate(String mobile) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(mobile).matches();
    }

}
