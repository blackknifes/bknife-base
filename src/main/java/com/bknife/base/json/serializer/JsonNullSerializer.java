package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

class JsonNullSerializer implements JsonSerializer<Object> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object value, JsonSerializeFeature feature) {
        buffer.append("null");
    }
}
