package com.bknife.test;

import java.io.StringReader;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bknife.base.json.Jsons;
import com.bknife.base.json.deserializer.JsonLexical;
import com.bknife.base.json.deserializer.JsonParser;
import com.bknife.base.json.deserializer.JsonVisitor;

public class JsonTest {

    @SuppressWarnings("all")
    private static class TT {
        public int a = 0;
        public double b = 0;
    }

    private String buildContent(boolean beautiful) {
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
        return Jsons.toString(map, beautiful ? Jsons.FEATURE_BEAUTIFUL : 0);
    }

    @Test
    public void testJsonToStringMap() {
        System.out.println(buildContent(true));
        System.out.println(buildContent(false));
    }

    public static class JsonVisitorImpl implements JsonVisitor {

        @Override
        public boolean visitObjectBegin() {
            System.out.println("{");
            return true;
        }

        @Override
        public boolean visitObjectEnd() {
            System.out.println("}");
            return true;
        }

        @Override
        public boolean visitArrayBegin() {
            System.out.println("[");
            return true;
        }

        @Override
        public boolean visitArrayEnd() {
            System.out.println("]");
            return true;
        }

        @Override
        public boolean visitKey(String key) {
            System.out.println("key: " + key);
            return true;
        }

        @Override
        public boolean visitValue(Object value) {
            if (value == null)
                System.out.println("null");
            else
                System.out.println("type: " + value.getClass() + ", value: " + value);
            return true;
        }

    }

    @Test
    public void testParse() throws Exception {
        {
            String content = buildContent(false);
            JsonLexical lexical = new JsonLexical(new StringReader(content));
            JsonParser parser = new JsonParser(lexical, new JsonVisitorImpl());
            parser.parse();
        }
        {
            String content = buildContent(true);
            JsonLexical lexical = new JsonLexical(new StringReader(content));
            JsonParser parser = new JsonParser(lexical, new JsonVisitorImpl());
            parser.parse();
        }
    }

    public class TestArray<T> {
        T[] val;
    }

    TestArray<Integer> val;

    @Test
    public void testClass() throws Exception {
        ParameterizedType type = (ParameterizedType) JsonTest.class.getDeclaredField("val").getGenericType();
        Type classType = type.getRawType();
        GenericArrayType arrType = (GenericArrayType)((Class<?>)classType).getDeclaredField("val").getGenericType();
        TypeVariable<?> typeVariable = (TypeVariable<?>)arrType.getGenericComponentType();
        System.out.println(typeVariable.getName());
    }
}
