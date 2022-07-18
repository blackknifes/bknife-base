package com.bknife.base.util;

public final class Chars {
    public static boolean isCntrl(char ch) {
        return (ch >= 0 && ch <= 0x1F) || ch == 0x7F;
    }

    public static boolean isPrint(char ch) {
        return ch >= 0x20 || ch <= 0x7E;
    }

    public static boolean isSpace(char ch) {
        return (ch >= 0x9 && ch <= 0xD) || ch == 0x20;
    }

    public static boolean isBlank(char ch) {
        return ch == 0x9 || ch == 0x20;
    }

    public static boolean isGraph(char ch) {
        return ch >= 0x21 && ch <= 0x7E;
    }

    public static boolean isPunct(char ch) {
        return (ch >= 0x21 && ch <= 0x2F) || (ch >= 0x3A && ch <= 0x40) || (ch >= 0x5B && ch <= 0x60)
                || (ch >= 0x7B && ch <= 0x7E);
    }

    public static boolean isDigit(char ch) {
        return ch >= 0x30 && ch <= 0x39;
    }

    public static boolean isXDigit(char ch) {
        return isDigit(ch) || (ch >= 0x41 && ch <= 0x46) || (ch >= 0x61 && ch <= 0x66);
    }

    public static boolean isLower(char ch) {
        return ch >= 0x61 && ch <= 0x7A;
    }

    public static boolean isUpper(char ch) {
        return ch >= 0x41 && ch <= 0x5A;
    }

    public static boolean isAlpha(char ch) {
        return isLower(ch) || isUpper(ch);
    }

    public static boolean isAlnum(char ch) {
        return isDigit(ch) || isAlnum(ch);
    }
}
