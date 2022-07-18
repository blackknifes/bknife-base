package com.bknife.base.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bknife.base.json.serializer.JsonSerializeFeature;
import com.bknife.base.json.serializer.JsonToString;

public final class Jsons implements JsonContants {
    private Jsons() {
    }

    public static <T> T toObject(String content, Class<T> clazz) {
        return JSONObject.parseObject(content, clazz);
    }

    public static Map<String, Object> toMap(String content) {
        return JSONObject.parseObject(content);
    }

    public static <T> List<T> toArray(String content, Class<T> clazz) {
        return JSONArray.parseArray(content, clazz);
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toArray(String content) {
        return JSONArray.parseArray(content);
    }

    public static String toString(Object object, int features) {
        return JsonToString.toString(object, new JsonSerializeFeature(features));
    }

    public static String toString(Object object) {
        return toString(object, 0);
    }
}
