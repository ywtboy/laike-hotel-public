package com.ywt.laike.hotel.util;

import java.math.BigDecimal;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author ywt
 */
public class DateUtils {
    private final static ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * LocalDateTime 转换为 Date
     * @param localDateTime 本地日期时间
     * @return 系统默认时区的 Date
     */
    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(DEFAULT_ZONE).toInstant());
    }

    /**
     * LocalDate 转换为 Date
     * @param localDate 本地日期
     * @return 系统默认时区的 Date time 为 0：00：00
     */
    public static Date getDate(LocalDate localDate) {
        return Date.from(LocalDateTime.of(localDate, LocalTime.of(0,0))
                .atZone(DEFAULT_ZONE).toInstant());
    }

    /**
     * LocalDateTime 转换为 Date
     * @param localDateTime 本地日期时间
     * @param zone 时区字符串 如 "Asia/Shanghai"
     * @return Date
     */
    public static Date getDate(LocalDateTime localDateTime, String zone) {
        return Date.from(localDateTime.atZone(ZoneId.of(zone)).toInstant());
    }

    /**
     * LocalDate 转换为 Date
     * @param localDate 本地日期
     * @param zone 时区字符串 如 "Asia/Shanghai"
     * @return 系统默认时区的 Date time 为 0：00：00
     */
    public static Date getDate(LocalDate localDate, String zone) {
        return Date.from(LocalDateTime.of(localDate, LocalTime.of(0,0))
                .atZone(ZoneId.of(zone)).toInstant());
    }

    /**
     * 给 Date 加上 day 天
     * @param date 要处理的 Date
     * @param day 天数 负数则减 day 天
     */
//    public static void addDate(Date date, int day) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, day);
//        date.setTime(calendar.getTimeInMillis());
//    }

    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        date.setTime(calendar.getTimeInMillis());
        return date;
    }

}
