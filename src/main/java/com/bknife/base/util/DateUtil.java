package com.bknife.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public abstract class DateUtil {
    private static final DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取毫秒
     * 
     * @return
     */
    public long nowTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取纳秒
     * 
     * @return
     */
    public long nowNanoTime() {
        return System.nanoTime();
    }

    /**
     * 当前时间对象
     * 
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 当前日期字符串
     * 
     * @return
     */
    public static String nowDate() {
        return formatDate(now());
    }

    /**
     * 当前时间字符串
     * 
     * @return
     */
    public static String nowDatetime() {
        return formatDatetime(now());
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 转为日期
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date toDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 转为时间
     * 
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static Date toDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * long转日期
     * 
     * @param value
     * @return
     */
    public static Date toDate(long value) {
        return new Date(value);
    }
}
