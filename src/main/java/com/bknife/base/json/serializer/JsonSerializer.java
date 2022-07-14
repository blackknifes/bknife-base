package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

/**
 * json序列化器
 */
public interface JsonSerializer<T> {
    /**
     * 序列化
     * 
     * @param buffer
     */
    public void serilize(JsonSerializer<Object> root, TokenBuffer buffer, T value, JsonSerializeFeature feature);
}
