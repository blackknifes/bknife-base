package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

class JsonNumberSerializer implements JsonSerializer<Object> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object value, JsonSerializeFeature feature) {
        buffer.append(value.toString());
    }
}
