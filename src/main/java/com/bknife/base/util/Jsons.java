package com.bknife.base.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;

public final class Jsons {
    private Jsons() {
    }

    public static <T> T toObject(String content, Class<T> clazz) {
        return JSON.parseObject(content, clazz, JSONReader.Feature.FieldBased,
                JSONReader.Feature.AllowUnQuotedFieldNames);
    }

    public static Map<String, Object> toMap(String content) {
        return JSON.parseObject(content, JSONReader.Feature.FieldBased, JSONReader.Feature.AllowUnQuotedFieldNames);
    }

    public static List<Object> toArray(String content) {
        return JSON.parseArray(content, JSONReader.Feature.FieldBased, JSONReader.Feature.AllowUnQuotedFieldNames);
    }

    public static String toString(Object object, boolean beatiful) {
        if (!beatiful)
            return toString(object);
        return JSON.toJSONString(object, JSONWriter.Feature.FieldBased, JSONWriter.Feature.PrettyFormat,
                JSONWriter.Feature.BrowserCompatible, JSONWriter.Feature.IgnoreErrorGetter);
    }

    public static String toString(Object object) {
        return JSON.toJSONString(object, JSONWriter.Feature.FieldBased, JSONWriter.Feature.BrowserCompatible,
                JSONWriter.Feature.IgnoreErrorGetter);
    }
}
