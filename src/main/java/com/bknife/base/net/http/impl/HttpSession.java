package com.bknife.base.net.http.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bknife.base.converter.ConverterUtils;
import com.bknife.base.net.http.Session;

public class HttpSession implements Session {
    private Map<Object, Object> attributes = new HashMap<Object, Object>();

    @Override
    public synchronized Collection<Object> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public synchronized Object getAttribute(Object key) {
        return attributes.get(key);
    }

    @Override
    public <T> T getAttribute(Object key, Class<T> clazz) {
        return getAttribute(key, clazz, null);
    }

    @Override
    public <T> T getAttribute(Object key, Class<T> clazz, T defaultValue) {
        Object val = getAttribute(key);
        if (val == null)
            return defaultValue;
        T tmp = ConverterUtils.convert(val, clazz);
        return tmp == null ? defaultValue : tmp;
    }

    @Override
    public int getAttributeInt(Object key) {
        return getAttributeInt(key, 0);
    }

    @Override
    public int getAttributeInt(Object key, int defaultValue) {
        return getAttribute(key, Integer.class, 0);
    }

    @Override
    public long getAttributeLong(Object key) {
        return getAttributeLong(key, 0);
    }

    @Override
    public long getAttributeLong(Object key, long defaultValue) {
        return getAttribute(key, Long.class, 0l);
    }

    @Override
    public double getAttributeDouble(Object key) {
        return getAttributeDouble(key, 0);
    }

    @Override
    public double getAttributeDouble(Object key, double defaultValue) {
        return getAttribute(key, Double.class, 0.0);
    }

    @Override
    public String getAttributeString(Object key) {
        return getAttributeString(key, "");
    }

    @Override
    public String getAttributeString(Object key, String defaultValue) {
        return getAttribute(key, String.class, "");
    }

}
