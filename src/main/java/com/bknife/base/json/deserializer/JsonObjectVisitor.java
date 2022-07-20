package com.bknife.base.json.deserializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bknife.base.util.ResolveType;

public class JsonObjectVisitor<T> implements JsonVisitor {
    private Class<T> type;

    private List<ResolveType> typeList = new ArrayList<>();
    private List<String> keys = new ArrayList<>();
    private List<Object> values = new ArrayList<>();

    private T result;

    @Override
    public boolean visitArrayBegin() {
        if (result == null)
            return false;
        values.add(new ArrayList<>());
        return true;
    }

    @Override
    public boolean visitArrayEnd() {
        return addToParent(takeValue());
    }

    @Override
    public boolean visitKey(String key) {
        

        return JsonVisitor.super.visitKey(key);
    }

    @Override
    public boolean visitObjectBegin() {
        if (result == null) {
            try {
                Constructor<T> constructor = type.getConstructor();
                constructor.setAccessible(true);
                result = constructor.newInstance();
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean visitObjectEnd() {
        return addToParent(takeValue());
    }

    @Override
    public boolean visitValue(Object value) {
        if (value == null)
            return addToParent(value);
        if (result == null)
            return false;
        return addToParent(value);
    }

    private String takeKey() {
        String key = keys.get(keys.size() - 1);
        keys.remove(keys.size() - 1);
        return key;
    }

    private Object takeValue() {
        Object value = values.get(values.size() - 1);
        values.remove(values.size() - 1);
        return value;
    }

    @SuppressWarnings("unchecked")
    private boolean addToParent(Object value) {
        if (values.isEmpty()) {
            this.result = (T) value;
            return true;
        }
        Object object = values.get(values.size() - 1);
        if (object instanceof Collection)
            ((Collection<Object>) object).add(value);
        else if (object instanceof Map)
            ((Map<String, Object>) object).put(takeKey(), value);
        else {
            Field field = findField(object.getClass(), takeKey());
            if (field == null)
                return true;
            try {
                field.set(object, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static Field findField(Class<?> type, String name) {
        while (type != null) {
            try {
                Field field = type.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {

            }
            type = type.getSuperclass();
        }
        return null;
    }
}
