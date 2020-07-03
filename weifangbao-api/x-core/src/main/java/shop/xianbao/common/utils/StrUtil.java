package shop.xianbao.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */
public class StrUtil {

    public static final int TYPE_NUM = 1;

    public static final int TYPE_ALPHABET = 2;

    public static final int TYPE_ALPHABET_LOWER = 3;

    public static final int TYPE_ALPHABET_UPPER = 4;

    public static final int TYPE_ALPHABET_NUM = 5;

    public static final int TYPE_ALPHABET_SPEC = 6;

    public static final int TYPE_ALPHABET_NUM_SPEC = 7;

    public static String generateRandomReal(int length, String range) {
        StringBuffer sb = new StringBuffer();
        int len = range.length();
        for (int i = 0; i < length; i++) {
            int random = (int)Math.round(Math.random() * (len - 1));
            sb.append(range.charAt(random));
        }
        return sb.toString();
    }

    public static String generateRandom(int length) {
        String range = "abcdefghijklmnopqrstuvwxyz";
        return generateRandomReal(length, range);
    }

    public static String generateRandom(int length, int type) {
        String range = "";
        String unm = "1234567890";
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLOWER = "abcdefghijklmnopqrstuvwxyz";
        String spec = "~!@#$%^&*()_+{}[\\|";
        switch (type) {
            case TYPE_NUM:
                range = unm;
                break;
            case TYPE_ALPHABET:
                range = alphabetUpper + alphabetLOWER;
                break;
            case TYPE_ALPHABET_UPPER:
                range = alphabetUpper;
                break;
            case TYPE_ALPHABET_NUM:
                range = alphabetUpper + alphabetLOWER + unm;
                break;
            case TYPE_ALPHABET_SPEC:
                range = alphabetUpper + alphabetLOWER + spec;
                break;
            case TYPE_ALPHABET_NUM_SPEC:
                range = alphabetUpper + alphabetLOWER + spec + unm;
                break;
            case TYPE_ALPHABET_LOWER:
            default:
                range = alphabetLOWER;
                break;
        }
        return generateRandomReal(length, range);
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else {
                    output += Character.toString(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 获取汉字串拼音首字母(大写)，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstUpCase(String chinese) {
        String result = getFirstSpell(chinese).toUpperCase();
        if (StringUtils.isNotBlank(result)) {
            return result.substring(0, 1).toUpperCase();
        } else {
            return null;
        }
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }

}
