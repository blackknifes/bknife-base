package com.bknife.base.json.serializer;

import java.util.Map;

import com.bknife.base.util.TokenBuffer;

class JsonMapSerializer implements JsonSerializer<Map<Object, Object>> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Map<Object, Object> map,
            JsonSerializeFeature feature) {

        int nextDepth = depth + 1;

        buffer.openBrace();
        if (feature.isBeatiful())
            buffer.lf();

        if (!map.isEmpty()) {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                if (feature.isBeatiful())
                    buffer.space(nextDepth << 1);
                root.serialize(root, nextDepth, buffer, entry.getKey().toString(), feature);
                buffer.colon();
                if (feature.isBeatiful())
                    buffer.space();
                root.serialize(root, nextDepth, buffer, entry.getValue(), feature);
                buffer.comma();
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
        buffer.closeBrace();
    }

}
