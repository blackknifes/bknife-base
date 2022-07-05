package com.bknife.base.util;

import java.io.File;

public abstract class Strings {
    private static final char[] HEXS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

    public static String addStartsWith(String str, String starts) {
        if (str.startsWith(starts))
            return str;
        return starts + str;
    }

    public static String removeStartsWith(String str, String starts) {
        if (str.startsWith(starts))
            return str.substring(starts.length());
        return str;
    }

    public static String addEndsWith(String str, String ends) {
        if (str.endsWith(ends))
            return str;
        return str + ends;
    }

    public static String removeEndsWithSeperator(String str, String ends) {
        if (str.endsWith(ends))
            return str.substring(0, str.length() - ends.length());
        return str;
    }

    public static String toLinuxSeperator(String str) {
        return str.replace('\\', '/');
    }

    public static String toWindowsSeperator(String str) {
        return str.replace('/', '\\');
    }

    public static String toNativeSeperator(String str) {
        return str.replaceAll("[\\\\/]", File.separator);
    }

    public static String toLf(String str) {
        return str.replaceAll("(\\r\\n)|\\n", "\n");
    }

    public static String toCrLf(String str) {
        return str.replaceAll("(\\r\\n)|\\n", "\r\n");
    }

    /**
     * 使用ch字符在开头补齐长度
     * 
     * @param str
     * @param length
     * @param ch
     * @return
     */
    public static String padStart(String str, int length, char ch) {
        int padCount = length - str.length();
        if (padCount <= 0)
            return str;

        StringBuffer buffer = new StringBuffer(padCount);
        for (int i = 0; i < padCount; i++)
            buffer.append(ch);
        return buffer.toString() + str;
    }

    /**
     * 使用ch字符在结尾补齐长度
     * 
     * @param str
     * @param length
     * @param ch
     * @return
     */
    public static String padEnd(String str, int length, char ch) {
        int padCount = length - str.length();
        if (padCount <= 0)
            return str;

        StringBuffer buffer = new StringBuffer(length);
        buffer.append(str);
        for (int i = 0; i < padCount; i++)
            buffer.append(ch);
        return buffer.toString();
    }

    public static String toHexString(byte[] data) {
        return toHexString(data, 0, data.length);
    }

    public static String toHexString(byte[] data, int offset, int length) {
        StringBuffer buffer = new StringBuffer(length << 1);

        for (int i = offset; i < length; i++) {
            byte val = data[i];
            buffer.append(HEXS[val & 0xF]);
            buffer.append(HEXS[val >> 4]);
        }
        return buffer.toString();
    }

    public static String toHexString(byte val) {
        return padStart(Integer.toString(val, 16), 2, '0');
    }

    public static String toHexString(short val) {
        return padStart(Integer.toString(val, 16), 4, '0');
    }

    public static String toHexString(int val) {
        return padStart(Integer.toString(val, 16), 8, '0');
    }

    public static String toHexString(long val) {
        return padStart(Long.toString(val, 16), 16, '0');
    }
}
