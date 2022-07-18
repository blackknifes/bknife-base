package com.bknife.base.json.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.bknife.base.json.annotation.Ignore;
import com.bknife.base.util.TokenBuffer;

class JsonObjectSerializer implements JsonSerializer<Object> {

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object value,
            JsonSerializeFeature feature) {
        int nextDepth = depth + 1;

        Class<?> currentClass = value.getClass();
        buffer.openBrace();

        if (feature.isBeatiful())
            buffer.lf();

        boolean empty = true;
        do {
            for (Field field : currentClass.getDeclaredFields()) {
                try {
                    if ((field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT
                            && !feature.isSerializeTransient())
                        continue;
                    if ((field.getAnnotation(Ignore.class)) != null
                            && !feature.isSerializeIgnore())
                        continue;

                    field.setAccessible(true);

                    if (feature.isBeatiful())
                        buffer.space(nextDepth << 1);

                    root.serialize(root, nextDepth, buffer, field.getName(), feature);
                    buffer.colon();
                    if (feature.isBeatiful())
                        buffer.space();
                    root.serialize(root, nextDepth, buffer, field.get(value), feature);
                    buffer.comma();
                    if (feature.isBeatiful())
                        buffer.lf();
                    empty = true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } while ((currentClass = currentClass.getSuperclass()) != null);
        if (!empty) {
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
