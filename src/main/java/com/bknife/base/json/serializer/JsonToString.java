package com.bknife.base.json.serializer;

import java.util.Collection;
import java.util.Map;

import com.bknife.base.json.JsonContants;
import com.bknife.base.util.TokenBuffer;

public final class JsonToString implements JsonContants, JsonSerializer<Object> {
    private static JsonSerializer<Object> arraySerializer = new JsonArraySerializer();
    private static JsonSerializer<Collection<Object>> collectionSerializer = new JsonCollectionSerializer();
    private static JsonSerializer<Map<Object, Object>> mapSerializer = new JsonMapSerializer();
    private static JsonSerializer<Object> stringSerializer = new JsonStringSerializer();
    private static JsonSerializer<Object> valueSerializer = new JsonNumberSerializer();
    private static JsonSerializer<Object> objectSerializer = new JsonObjectSerializer();
    private static JsonSerializer<Object> nullSerializer = new JsonNullSerializer();

    public static String toString(Object value, JsonSerializeFeature feature) {
        JsonToString jsonToString = new JsonToString();
        TokenBuffer buffer = new TokenBuffer();
        jsonToString.serialize(jsonToString, 0, buffer, value, feature);
        return buffer.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object value, JsonSerializeFeature feature) {
        if (value == null) {
            nullSerializer.serialize(root, depth, buffer, null, feature);
            return;
        }

        Class<?> clazz = value.getClass();
        if (clazz == char.class || Character.class.isAssignableFrom(clazz))
            stringSerializer.serialize(root, depth, buffer, value, feature);
        else if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)
                || Character.class.isAssignableFrom(clazz))
            valueSerializer.serialize(root, depth, buffer, value, feature);
        else if (String.class.isAssignableFrom(clazz))
            stringSerializer.serialize(root, depth, buffer, value, feature);
        else if (clazz.isArray())
            arraySerializer.serialize(root, depth, buffer, value, feature);
        else if (Collection.class.isAssignableFrom(clazz))
            collectionSerializer.serialize(root, depth, buffer, (Collection<Object>) value, feature);
        else if (Map.class.isAssignableFrom(clazz))
            mapSerializer.serialize(root, depth, buffer, (Map<Object, Object>) value, feature);
        else
            objectSerializer.serialize(root, depth, buffer, value, feature);
    }
}
