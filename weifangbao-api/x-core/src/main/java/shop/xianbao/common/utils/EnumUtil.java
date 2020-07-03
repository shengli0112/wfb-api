package shop.xianbao.common.utils;

/**
 * 枚举工具类
 */
public class EnumUtil {

    /**
     * 根据 Key 获取枚举
     */
    public static <T extends Enum> T getEnumBycode(Class<T> clazz, int code) {
        for(T _enum : clazz.getEnumConstants()){
            if(code == _enum.ordinal()){
                return _enum;
            }
        }

        return null;
    }
}
