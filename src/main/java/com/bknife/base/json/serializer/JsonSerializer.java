package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

/**
 * json序列化器
 */
interface JsonSerializer<T> {
    /**
     * 序列化
     * 
     * @param buffer
     */
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, T value, JsonSerializeFeature feature);
}
