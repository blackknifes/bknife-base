package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

public class JsonStringSerializer implements JsonSerializer<String> {

    @Override
    public void serilize(JsonSerializer<Object> root, TokenBuffer buffer, String value, JsonSerializeFeature feature) {
        buffer.string(value);
    }
}
