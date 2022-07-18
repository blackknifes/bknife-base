package com.bknife.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bknife.base.json.Jsons;

public class JsonTest {

    private static class TT
    {
        public int a = 0;
        public double b = 0;
    }

    @Test
    public void testJsonToStringMap() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Object> collection = new ArrayList<>();
        Object[] arr = new Object[7];
        map.put("50", 50);
        map.put("50.555", 50.555);
        map.put("map2", map2);
        map.put("collection", collection);
        map.put("array", arr);

        map2.put("name", "map2");

        collection.add(5);
        collection.add(true);
        collection.add(12.5);
        collection.add("test");
        collection.add(arr);

        arr[0] = "arr1";
        arr[1] = "arr2";
        arr[2] = "arr3";
        arr[3] = 5;
        arr[4] = "\f\t\n\r😠中文";
        TT tt = new TT();
        tt.a = 5;
        tt.b = 5.5;
        arr[5] = tt;

        System.out.println(Jsons.toString(map, Jsons.FEATURE_BEAUTIFUL));
    }
}
