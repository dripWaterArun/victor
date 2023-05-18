package io.github.arun.util.regex;

import io.github.arun.regex.DateRegexs;

/**
 * 日期 正则表达式 工具类
 * @author Arun
 * @date
 */
public class DateRegexUtil {

    /**
     * 判断是否是日期格式的字符串
     * @return true 是字符串格式  false 不是字符串格式
     * @author Arun
     * @date 2023-05-18 14:45
     */
    public static boolean isDateStr(Object obj){
        if (obj !=null) {
            String strDate = String.valueOf(obj);
            if (strDate.matches(DateRegexs.YYYY_MM_DD_HH_MM_SS)){
                return true;
            }else if (strDate.matches(DateRegexs.YYYY_MM_DD)){
                return true;
            } else if (strDate.matches(DateRegexs.YYYY)) {
                return true;
            }else if (strDate.matches(DateRegexs.YYYY_MM)) {
                return true;
            }else if (strDate.matches(DateRegexs.HH_MM_SS)) {
                return true;
            }else if (strDate.matches(DateRegexs.YYYYMMDDHHMMSS)) {
                return true;
            }
        }
        return false;
    }

}
