package com.bknife.base.net.http;

import java.util.Collection;

public interface Session {

    /**
     * 获取所有属性名称
     * 
     * @return
     */
    public Collection<Object> getAttributeNames();

    /**
     * 获取属性对象
     * 
     * @param key
     * @return
     */
    public Object getAttribute(Object key);

    /**
     * 获取指定类型属性对象,对象将自动转换为clazz类型
     * 
     * @param key
     * @return
     */
    public <T> T getAttribute(Object key, Class<T> clazz);

    /**
     * 获取指定类型属性对象,对象将自动转换为clazz类型
     * 
     * @param key
     * @param defaulValue 默认值
     * @return
     */
    public <T> T getAttribute(Object key, Class<T> clazz, T defaultValue);

    /**
     * 获取int类型属性
     * 
     * @param key
     * @return
     */
    public int getAttributeInt(Object key);

    /**
     * 获取int类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public int getAttributeInt(Object key, int defaultValue);

    /**
     * 获取long类型属性
     * 
     * @param key
     * @return
     */
    public long getAttributeLong(Object key);

    /**
     * 获取long类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public long getAttributeLong(Object key, long defaultValue);

    /**
     * 获取double类型属性
     * 
     * @param key
     * @return
     */
    public double getAttributeDouble(Object key);

    /**
     * 获取double类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public double getAttributeDouble(Object key, double defaultValue);

    /**
     * 获取String类型属性
     * 
     * @param key
     * @return
     */
    public String getAttributeString(Object key);

    /**
     * 获取String类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public String getAttributeString(Object key, String defaultValue);
}
