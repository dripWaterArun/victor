package io.github.arun.util.date;

import io.github.arun.constant.DateConsts;
import io.github.arun.regex.DateRegexs;
import io.github.arun.util.regex.DateRegexUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * 日期格式化工具类
 * @author Arun
 * @date 2023-05-18 14:51
 */
public class DateFormatUtil {

    /**
     * 日期格式化，返回日期类型（LocalDateTime）
     * @param strDate 日期格式的字符串
     * @return 如参数为 null 返回当前时间
     */
    public static LocalDateTime localDateTimeFormat(Object strDate){
        if (DateRegexUtil.isDateStr(strDate)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            String time = " 00:00:00";
            //转换为字符串
            String str = String.valueOf(strDate);
            //获取月
            int month = localDateTime.get(ChronoField.MONTH_OF_YEAR);
            //获取日
            int day = localDateTime.getDayOfMonth();

            //默认是 LocalDateTime 格式的字符串
            if (str.matches(DateRegexs.YYYY_MM_DD)){
                //获取 时分秒 拼接成 YYY-MM-DD HH:mm:ss 格式
                str = str + time;
            } else if (str.matches(DateRegexs.YYYY_MM)){
                //获取 月 日 拼接成 YYY-MM-DD HH:mm:ss 格式
                str = str + day + time;
            } else if (str.matches(DateRegexs.YYYY)) {
                //获取月 日 拼接成 YYY-MM-DD HH:mm:ss 格式
                str = str + month + day + time;
            } else if(str.matches(DateRegexs.YYYYMMDDHHMMSS)){
                //字符串，转化为 yyyy-MM-dd HH:mm:ss 格式
                //字符串转日期
                DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(DateConsts.YYYYMMDDHHMMSS);
                localDateTime = LocalDateTime.parse(str, timeDtf);
            }

            //判断是否符合日期格式化规范
            if (str.matches(DateRegexs.YYYY_MM_DD_HH_MM_SS)){
                //字符串转日期
                DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(DateConsts.YYYY_MM_DD_HH_MM_SS);
                return LocalDateTime.parse(str, timeDtf);
            }

            return localDateTime;
        }

        return null;
    }

    /**
     * 日期格式化，返回日期类型（LocalDate）
     * @param strDate 日期格式的字符串
     * @return 如参数为 null 返回当前时间
     */
    public static LocalDate localDateFormat(Object strDate) {
        if (DateRegexUtil.isDateStr(strDate)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            //转换为字符串
            String str = String.valueOf(strDate);
            //获取月
            int month = localDateTime.get(ChronoField.MONTH_OF_YEAR);
            //获取日
            int day = localDateTime.getDayOfMonth();
            //是 LocalDate 格式的字符串
            if (str.matches(DateRegexs.YYYY_MM_DD_HH_MM_SS)){
                //字符串截取，只取 年 月 日
                //获取空格的位置
                int end = str.indexOf("\\t");
                //从头开始，一直截取到空格之前的位置
                str = str.substring(0,end);
            } else if (str.matches(DateRegexs.YYYY)){
                //获取月 日 拼接成 YYY-MM-DD 格式
                str = str + month + day;
            } else if (str.matches(DateRegexs.YYYY_MM)){
                //获取月 日 拼接成 YYY-MM-DD 格式
                str = str + day;
            }

            //是 LocalDate 格式的字符串
            if (str.matches(DateRegexs.YYYY_MM_DD)){
                //字符串转日期
                DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(DateConsts.YYYY_MM_DD);
                return LocalDate.parse(str, timeDtf);
            }
        }
        return null;
    }

    /**
     * 日期格式化，返回日期类型（LocalTime）
     * @param strDate 日期格式的字符串
     * @return 如参数为 null 返回当前时间
     */
    public static LocalTime localTimeFormat(Object strDate) {
        if (DateRegexUtil.isDateStr(strDate)) {
            //转换为字符串
            String str = String.valueOf(strDate);
            //是 LocalTime 格式的字符串
            if (str.matches(DateRegexs.HH_MM_SS)){
                //字符串转日期
                DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(DateConsts.HH_MM_SS);
                return LocalTime.parse(str, timeDtf);
            }
        }
        return null;
    }

    /**
     * 日期格式化，返回日期类型（Date）
     * @param strDate
     * @return
     */
    public static Date dateFormat(Object strDate){
        //普通日期格式
        if (DateRegexUtil.isDateStr(strDate)) {
            //转换为字符串
            String str = String.valueOf(strDate);
            SimpleDateFormat format = new SimpleDateFormat(strFormat(str));
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字符串转化日期的格式
     * @param strDate 字符串
     * @return 字符串应该转化为日期的格式（如果格式不对，默认返回 yyyy-MM-dd）
     */
    public static String strFormat(String strDate){
        if (strDate.length() == DateConsts.YYYY_MM_DD_HH_MM_SS.length()) {
            return DateConsts.YYYY_MM_DD_HH_MM_SS;
        }else if (strDate.length() == DateConsts.YYYY.length()){
            return DateConsts.YYYY;
        }else if (strDate.length() == DateConsts.YYYY_MM.length()){
            return DateConsts.YYYY_MM;
        }else if (strDate.length() == DateConsts.YYYYMMDDHHMMSS.length()){
            return DateConsts.YYYYMMDDHHMMSS;
        }
        return DateConsts.YYYY_MM_DD;
    }

}
