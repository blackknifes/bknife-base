package com.bknife.base.util;

import java.util.Collection;
import java.util.Map;

public class Validates {

    /**
     * 验证字符串是否为空或空白字符
     * 
     * @param str
     * @return
     */
    public static boolean isEmptyBlank(String str) {
        if (str == null)
            return true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isSpaceChar(str.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * 验证字符串不为空白字符
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmptyBlank(String str) {
        return !isEmptyBlank(str);
    }

    /**
     * 验证字符串长度范围
     * 
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isRangeOfSize(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    /**
     * 验证不符合字符串长度范围的条件
     * 
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isNotRangeOfSize(String str, int min, int max) {
        return !isRangeOfSize(str, min, max);
    }

    /**
     * 验证容器是否为空
     * 
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 验证容器是否为空
     * 
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 验证容器不为空
     * 
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 验证数组是否为空
     * 
     * @param <T>
     * @param arr
     * @return
     */
    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 验证数组不为空
     * 
     * @param <T>
     * @param arr
     * @return
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
