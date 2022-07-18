package com.bknife.base.json.serializer;

import java.lang.reflect.Array;

import com.bknife.base.util.TokenBuffer;

class JsonArraySerializer implements JsonSerializer<Object> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object array,
            JsonSerializeFeature feature) {
        int nextDepth = depth + 1;
        buffer.openBracket(); // [
        if (feature.isBeatiful())
            buffer.lf();

        int len = Array.getLength(array);
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                if (feature.isBeatiful())
                    buffer.space(nextDepth << 1);
                root.serialize(root, nextDepth, buffer, Array.get(array, i), feature); // value
                buffer.comma(); // ,
                if (feature.isBeatiful())
                    buffer.lf();
            }
            if (feature.isBeatiful())
                buffer.deleteCharAt(buffer.length() - 2);
            else
                buffer.deleteLast();
        }

        if (feature.isBeatiful())
            buffer.space(depth << 1);
        buffer.closeBracket(); // ]
    }
}
