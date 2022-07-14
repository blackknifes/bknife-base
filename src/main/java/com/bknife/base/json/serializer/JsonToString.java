package com.bknife.base.json.serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import com.bknife.base.converter.ConverterUtils;
import com.bknife.base.json.JsonContants;
import com.bknife.base.json.annotation.Ignore;
import com.bknife.base.util.TokenBuffer;

final class JsonToString implements JsonContants {
    public static String toString(Object object, int feature) {
        TokenBuffer buffer = new TokenBuffer();
        boolean beautiful = (feature & FEATURE_BEAUTIFUL) == FEATURE_BEAUTIFUL;
        if (beautiful)
            toStringBeautiful(buffer, object, feature, 0);
        else
            toStringCommon(buffer, object, feature);
        return buffer.toString();
    }

    @SuppressWarnings("unchecked")
    public static void toStringCommon(TokenBuffer buffer, Object object, int feature) {
        if (object == null) {
            buffer.append("null");
            return;
        }
        Class<?> clazz = object.getClass();
        if (object instanceof String) // 字符串
            buffer.string(object.toString());
        else if (clazz.isPrimitive() || ConverterUtils.isWrapClass(clazz))
            buffer.append(object.toString());
        else if (clazz.isArray()) { // 数组
            buffer.openBracket();
            int len = Array.getLength(object);
            for (int i = 0; i < len; i++) {
                Object val = Array.get(object, i);
                toStringCommon(buffer, val, feature);
                buffer.comma();
            }
            if (len != 0)
                buffer.removeLast();
            buffer.closeBracket();
            return;
        } else if (Collection.class.isAssignableFrom(clazz)) { // 顺序容器
            buffer.openBracket();
            Collection<Object> collection = (Collection<Object>) object;
            for (Object val : collection) {
                toStringCommon(buffer, val, feature);
                buffer.comma();
            }
            if (!collection.isEmpty())
                buffer.removeLast();
            buffer.closeBracket();
        } else if (Map.class.isAssignableFrom(clazz)) { // 关系容器
            buffer.openBrace();
            Map<Object, Object> map = (Map<Object, Object>) object;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
                buffer.string(key.toString());
                buffer.colon();
                toStringCommon(buffer, val, feature);
                buffer.comma();
            }
            if (!map.isEmpty())
                buffer.removeLast();
            buffer.closeBrace();
        } else {
            do {
                buffer.openBrace();
                boolean empty = true;
                for (Field field : clazz.getDeclaredFields()) {
                    // 有transient关键词或者有ignore注解，跳过
                    if ((field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT
                            && (feature & FEATURE_SERIALIZE_TRANSIENT) != FEATURE_SERIALIZE_TRANSIENT)
                        continue;

                    if (field.getAnnotation(Ignore.class) != null
                            && (feature & FEATURE_SERIALIZE_IGNORE) != FEATURE_SERIALIZE_IGNORE)
                        continue;
                    empty = false;
                    buffer.string(field.getName());
                    buffer.colon();
                    field.setAccessible(true);
                    try {
                        toStringCommon(buffer, field.get(object), feature);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buffer.comma();
                }
                if (!empty)
                    buffer.removeLast();
                buffer.closeBrace();
            } while ((clazz = clazz.getSuperclass()) != null);
        }
    }

    // TODO: toStringBeautiful
    public static void toStringBeautiful(TokenBuffer buffer, Object object, int feature, int depth) {
        Class<?> clazz = object.getClass();
        boolean beautiful = (feature & FEATURE_BEAUTIFUL) == FEATURE_BEAUTIFUL;
        do {
            for (Field field : clazz.getDeclaredFields()) {
                // 有transient关键词或者有ignore注解，跳过
                if ((field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT
                        && (feature & FEATURE_SERIALIZE_TRANSIENT) != FEATURE_SERIALIZE_TRANSIENT)
                    continue;

                if (field.getAnnotation(Ignore.class) != null
                        && (feature & FEATURE_SERIALIZE_IGNORE) != FEATURE_SERIALIZE_IGNORE)
                    continue;

                if (beautiful)
                    buffer.space(depth << 1);
                buffer.string(field.getName());
                if (beautiful)
                    buffer.space();
                buffer.colon();
                if (beautiful)
                    buffer.space();
            }
        } while ((clazz = clazz.getSuperclass()) != null);
    }
}
