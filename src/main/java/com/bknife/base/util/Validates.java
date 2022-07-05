package com.bknife.base.util;

public class Validates {

    public static boolean isEmptyBlank(String str) {
        if (str == null)
            return true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isSpaceChar(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isNotEmptyBlank(String str)
    {
        return !isEmptyBlank(str);
    }

    public static boolean isRangeOfSize(String str, int min, int max)
    {
        return str.length() >= min && str.length() <= max;
    }

    public static boolean isNotRangeOfSize(String str, int min, int max)
    {
        return !isRangeOfSize(str, min, max);
    }
}
