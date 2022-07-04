package com.bknife.test;

import java.util.Date;

import org.junit.Test;

import com.bknife.base.converter.ConverterUtils;

public class ConvertUtilsTest {
    @Test
    public void testConvertUtils()
    {
        System.out.println(ConverterUtils.convert(33.698, long.class));
        System.out.println(ConverterUtils.convert(1, long.class));
        System.out.println(ConverterUtils.convert(53, boolean.class));
        System.out.println(ConverterUtils.convert(true, long.class));
        System.out.println(ConverterUtils.convert(true, String.class));
        System.out.println(ConverterUtils.convert("1991-08-08 15:22:33", Date.class));
    }
}
