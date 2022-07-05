package com.bknife.base.util;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;

/**
 * json静态工具类
 */
public abstract class Jsons {
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static byte[] toJsonBytes(Object object) {
        return JSON.toJSONBytes(object);
    }

    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> T toObject(InputStream inputStream, Class<T> clazz) {
        return JSON.parseObject(inputStream, clazz);
    }

    public static <T> T toObject(Reader reader, Class<T> clazz) {
        return JSON.parseObject(reader, clazz);
    }

    public static Collection<Object> toList(byte[] bytes) {
        return JSON.parseArray(bytes);
    }

    public static Collection<Object> toList(String json) {
        return JSON.parseArray(json);
    }

    public static Collection<Object> toList(InputStream inputStream) {
        return JSON.parseArray(inputStream);
    }

    public static Map<String, Object> toMap(String json) {
        return JSONObject.parseObject(json);
    }
}
