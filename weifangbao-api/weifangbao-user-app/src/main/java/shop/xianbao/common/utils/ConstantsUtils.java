package shop.xianbao.common.utils;

import org.apache.commons.lang.StringUtils;
import shop.xianbao.common.constants.Constants;

import java.util.regex.Pattern;

/**
 * @author wdp
 * Created by Ruitukeji on 2018/12/29.
 */
public class ConstantsUtils {

    /**
     * 判断是否为手机号码
     *
     * @param mobile 手机号码
     * @return 是否为手机号码
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            Pattern pattern = Pattern.compile(Constants.MobilePattern);
            return pattern.matcher(mobile).matches();
        }
        return false;
    }
}
