package com.bknife.test;

import java.util.Collection;

import org.junit.Test;

import com.bknife.base.util.Classs;

public class ClasssTest {
    @Test
    public void testScanClass() throws Exception {
        Collection<Class<?>> classList = Classs.scanClasses("com.alibaba.fastjson");
        for (Class<?> clazz : classList)
            System.out.println(clazz);
    }
}
