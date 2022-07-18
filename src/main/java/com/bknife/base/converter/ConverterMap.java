package com.bknife.base.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bknife.base.util.tuple.Tuple3;

public class ConverterMap<V> {
    private Map<Class<?>, Map<Class<?>, V>> topMap = new HashMap<Class<?>, Map<Class<?>, V>>();

    public V get(Class<?> key1, Class<?> key2) {
        Map<Class<?>, V> map = topMap.get(key1);
        if (map == null)
            return null;
        return map.get(key2);
    }

    public void put(Class<?> key1, Class<?> key2, V val) {
        Map<Class<?>, V> map = topMap.get(key1);
        if (map == null) {
            map = new HashMap<Class<?>, V>();
            topMap.put(key1, map);
        }
        map.put(key2, val);
    }

    public boolean containsKey(Class<?> key1, Class<?> key2) {
        Map<Class<?>, V> map = topMap.get(key1);
        if (map == null)
            return false;
        return map.containsKey(key2);
    }

    public Collection<Tuple3<Class<?>, Class<?>, V>> entriesByKey2(Class<?> key) {
        List<Tuple3<Class<?>, Class<?>, V>> list = new ArrayList<Tuple3<Class<?>, Class<?>, V>>();
        for (Map.Entry<Class<?>, Map<Class<?>, V>> entry : topMap.entrySet()) {
            Map<Class<?>, V> subMap = entry.getValue();
            for (Map.Entry<Class<?>, V> subEntry : subMap.entrySet()) {
                if (key == subEntry.getKey())
                    list.add(new Tuple3<>(entry.getKey(), subEntry.getKey(), subEntry.getValue()));
            }
        }
        return list;
    }

    public Collection<Tuple3<Class<?>, Class<?>, V>> entries() {
        List<Tuple3<Class<?>, Class<?>, V>> list = new ArrayList<Tuple3<Class<?>, Class<?>, V>>();
        for (Map.Entry<Class<?>, Map<Class<?>, V>> entry : topMap.entrySet()) {
            Map<Class<?>, V> subMap = entry.getValue();
            for (Map.Entry<Class<?>, V> subEntry : subMap.entrySet()) {
                list.add(new Tuple3<>(entry.getKey(), subEntry.getKey(), subEntry.getValue()));
            }
        }
        return list;
    }
}
