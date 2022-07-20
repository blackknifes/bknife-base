package com.bknife.base.json.deserializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonValueDeserializerVisitor implements JsonVisitor {
    private List<String> keys = new ArrayList<>();
    private List<Object> values = new ArrayList<>();
    private Object result;

    public Object getResult() {
        return result;
    }

    @Override
    public boolean visitArrayBegin() {
        addArray();
        return true;
    }

    @Override
    public boolean visitArrayEnd() {
        addToParent(takeValue());
        return true;
    }

    @Override
    public boolean visitKey(String key) {
        keys.add(key);
        return true;
    }

    @Override
    public boolean visitObjectBegin() {
        addMap();
        return true;
    }

    @Override
    public boolean visitObjectEnd() {
        addToParent(takeValue());
        return true;
    }

    @Override
    public boolean visitValue(Object value) {
        addToParent(value);
        return true;
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
    private void addToParent(Object value) {
        if (values.isEmpty()) {
            this.result = value;
            return;
        }
        Object object = values.get(values.size() - 1);
        if (object instanceof Collection)
            ((Collection<Object>) object).add(value);
        else if (object instanceof Map) {
            ((Map<String, Object>) object).put(takeKey(), value);
        }
    }

    private void addArray() {
        values.add(new ArrayList<Object>());
    }

    private void addMap() {
        values.add(new LinkedHashMap<String, Object>());
    }
}
