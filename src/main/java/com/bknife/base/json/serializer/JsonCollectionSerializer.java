package com.bknife.base.json.serializer;

import java.util.Collection;

import com.bknife.base.util.TokenBuffer;

class JsonCollectionSerializer implements JsonSerializer<Collection<Object>> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Collection<Object> collection,
            JsonSerializeFeature feature) {
        int nextDepth = depth + 1;
        buffer.openBracket(); // [
        if (feature.isBeatiful())
            buffer.lf();

        int i = 0;
        if (!collection.isEmpty()) {
            for (Object object : collection) {
                if (feature.isBeatiful())
                    buffer.space(nextDepth << 1);
                root.serialize(root, nextDepth, buffer, object, feature); // value
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
