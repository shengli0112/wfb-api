package shop.xianbao.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @program: PinYinUtils
 * @description:
 * @author: yh
 * @create: 2019-11-12 14:36
 **/
public class PinYinUtils {

    /**
     * 将文字转为汉语拼音
     *
     * @param chineseLanguage 要转成拼音的中文
     */
    public static String toHanyuPinyin(String chineseLanguage) {
        char[] cl_chars = chineseLanguage.trim().toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (char cl_char : cl_chars) {
                // 如果字符是中文,则将中文转为汉语拼音
                if (String.valueOf(cl_char).matches("[\u4e00-\u9fa5]+")) {
                    pinyin.append(PinyinHelper.toHanyuPinyinStringArray(cl_char, defaultFormat)[0] + " ");
                } else {
                    // 如果字符不是中文,则不转换
                    pinyin.append(cl_char);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return pinyin.toString();
    }

    /**
     * 拼音首字母大写
     *
     * @param ChineseLanguage
     * @return
     */
    public static String getFirstLettersUp(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    /**
     * 拼音首字母小写
     *
     * @param ChineseLanguage
     * @return
     */
    public static String getFirstLettersLo(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * 取拼音首字母
     *
     * @param ChineseLanguage
     * @param caseType
     * @return
     */
    public static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部大写或小写
        defaultFormat.setCaseType(caseType);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (char cl_char : cl_chars) {
                String str = String.valueOf(cl_char);
                if (str.matches("[\u4e00-\u9fa5]+")) {
                    // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    pinyin.append(PinyinHelper.toHanyuPinyinStringArray(cl_char, defaultFormat)[0].substring(0, 1));
                } else if (str.matches("[0-9]+")) {
                    // 如果字符是数字,取数字
                    pinyin.append(cl_char);
                } else if (str.matches("[a-zA-Z]+")) {
                    // 如果字符是字母,取字母
                    pinyin.append(cl_char);
                } else {
                    // 否则不转换
                    //如果是标点符号的话，带着
                    pinyin.append(cl_char);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return pinyin.toString();
    }

    /**
     * 取汉字的拼音首字母
     *
     * @param ChineseLanguage
     * @return
     */
    public static String getPinyinInitial(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String pinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (char cl_char : cl_chars) {
                String str = String.valueOf(cl_char);
                if (str.matches("[\u4e00-\u9fa5]+")) {
                    // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    pinyin += PinyinHelper.toHanyuPinyinStringArray(cl_char, defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {
                    // 如果字符是数字,取数字
                    pinyin += cl_char;
                } else if (str.matches("[a-zA-Z]+")) {
                    // 如果字符是字母,取字母
                    pinyin += cl_char;
                } else {
                    // 否则不转换
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return pinyin;
    }
}
