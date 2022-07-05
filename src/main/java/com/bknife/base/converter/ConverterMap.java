package com.bknife.base.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bknife.base.util.tuple.Tuple3;

public class ConverterMap<K1, K2, V> {
    private Map<K1, Map<K2, V>> topMap = new HashMap<K1, Map<K2, V>>();

    public V get(K1 key1, K2 key2) {
        Map<K2, V> map = topMap.get(key1);
        if (map == null)
            return null;
        return map.get(key2);
    }

    public void put(K1 key1, K2 key2, V val) {
        Map<K2, V> map = topMap.get(key1);
        if (map == null) {
            map = new HashMap<K2, V>();
            topMap.put(key1, map);
        }
        map.put(key2, val);
    }

    public boolean containsKey(K1 key1, K2 key2) {
        Map<K2, V> map = topMap.get(key1);
        if (map == null)
            return false;
        return map.containsKey(key2);
    }

    public Collection<Tuple3<K1, K2, V>> entriesByKey2(K2 key) {
        List<Tuple3<K1, K2, V>> list = new ArrayList<Tuple3<K1, K2, V>>();
        for (Map.Entry<K1, Map<K2, V>> entry : topMap.entrySet()) {
            Map<K2, V> subMap = entry.getValue();
            for (Map.Entry<K2, V> subEntry : subMap.entrySet()) {
                if (key == subEntry.getKey())
                    list.add(new Tuple3<>(entry.getKey(), subEntry.getKey(), subEntry.getValue()));
            }
        }
        return list;
    }

    public Collection<Tuple3<K1, K2, V>> entries() {
        List<Tuple3<K1, K2, V>> list = new ArrayList<Tuple3<K1, K2, V>>();
        for (Map.Entry<K1, Map<K2, V>> entry : topMap.entrySet()) {
            Map<K2, V> subMap = entry.getValue();
            for (Map.Entry<K2, V> subEntry : subMap.entrySet()) {
                list.add(new Tuple3<>(entry.getKey(), subEntry.getKey(), subEntry.getValue()));
            }
        }
        return list;
    }
}
