package com.bknife.base.json.serializer;

import java.lang.reflect.Array;
import java.util.Collection;

import com.bknife.base.util.TokenBuffer;

public class JsonArraySerializer implements JsonSerializer<Object> {

    @Override
    public void serilize(JsonSerializer<Object> root, TokenBuffer buffer, Object array, JsonSerializeFeature feature) {
        buffer.openBracket();
        int len = Array.getLength(array);
        for (int i = 0; i < len; i++)
            root.serilize(root, buffer, Array.get(array, i), feature);
        if (len != 0)
            buffer.removeLast();
        buffer.closeBracket();
    }
}
