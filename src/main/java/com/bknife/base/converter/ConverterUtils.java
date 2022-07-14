package com.bknife.base.converter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bknife.base.util.Classs;
import com.bknife.base.util.ResolveType;
import com.bknife.base.util.tuple.Tuple2;
import com.bknife.base.util.tuple.Tuple3;

/**
 * 转换工具
 */
public class ConverterUtils {

    private static Map<Class<?>, Class<?>> wrapClassToPrimitive = new HashMap<Class<?>, Class<?>>();
    private static Map<Class<?>, Class<?>> primitiveClassToWrap = new HashMap<Class<?>, Class<?>>();
    private static ConverterMap<Class<?>, Class<?>, Converter<?, ?>> converters = new ConverterMap<Class<?>, Class<?>, Converter<?, ?>>();

    static {
        init();
    }

    @SuppressWarnings("all")
    private static void init() {
        // 添加包装类到primitive类的映射
        wrapClassToPrimitive.put(Boolean.class, boolean.class);
        wrapClassToPrimitive.put(Byte.class, byte.class);
        wrapClassToPrimitive.put(Character.class, char.class);
        wrapClassToPrimitive.put(Short.class, short.class);
        wrapClassToPrimitive.put(Integer.class, int.class);
        wrapClassToPrimitive.put(Long.class, long.class);
        wrapClassToPrimitive.put(Float.class, float.class);
        wrapClassToPrimitive.put(Double.class, double.class);

        // 添加primitive类到包装类的映射
        primitiveClassToWrap.put(boolean.class, Boolean.class);
        primitiveClassToWrap.put(byte.class, Byte.class);
        primitiveClassToWrap.put(char.class, Character.class);
        primitiveClassToWrap.put(short.class, Short.class);
        primitiveClassToWrap.put(int.class, Integer.class);
        primitiveClassToWrap.put(long.class, Long.class);
        primitiveClassToWrap.put(float.class, Float.class);
        primitiveClassToWrap.put(double.class, Double.class);

        // 所有与Number有关的转换器
        List<Tuple2<Converter<?, ?>, Class<?>>> numberConverters = new ArrayList<Tuple2<Converter<?, ?>, Class<?>>>();
        try {
            // 扫描impl下所有转换器类
            Collection<Class<?>> classList = Classs.scanClasses(
                    ConverterUtils.class.getPackage().getName() + ".impl",
                    Converter.class);

            for (Class<?> clazz : classList) {
                if (clazz == Converter.class)
                    continue;
                try {
                    for (Type interfaceType : clazz.getGenericInterfaces()) {
                        // 遍历所有泛型接口
                        ResolveType type = ResolveType.resolve(interfaceType);
                        Class<?> fromClass = type.getGenericClass(0);
                        Class<?> toClass = type.getGenericClass(1);
                        // from类型可以直接指定给to类型，跳过
                        if (toClass.isAssignableFrom(fromClass))
                            continue;
                        // 创建转换器实例
                        Converter converter = (Converter) clazz.newInstance();
                        converters.put(fromClass, toClass, converter);
                        if (Number.class == fromClass) {
                            // 如果from类是Number类，则创建Number类为中间类的二级转换器
                            addConverter(converter, Byte.class, toClass);
                            addConverter(converter, Short.class, toClass);
                            addConverter(converter, Integer.class, toClass);
                            addConverter(converter, Long.class, toClass);
                            addConverter(converter, Float.class, toClass);
                            addConverter(converter, Double.class, toClass);
                            numberConverters.add(new Tuple2(converter, toClass));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Tuple3<Class<?>, Class<?>, Converter<?, ?>> entry : converters.entries()) {
            Class<?> fromClass = entry.getValue1();
            Class<?> toClass = entry.getValue2();
            Converter<?, ?> converter = entry.getValue3();
            addConverterNotExists(converter, fromClass, toClass.getSuperclass());
            for (Class<?> toInterface : toClass.getInterfaces())
                addConverterNotExists(converter, fromClass, toInterface);

            if (Number.class == toClass) {
                for (Tuple2<Converter<?, ?>, Class<?>> numberEntry : numberConverters) {
                    if (converters.containsKey(fromClass, numberEntry.getValue2()))
                        continue;
                    converters.put(fromClass, numberEntry.getValue2(),
                            converter.andThen((Converter) numberEntry.getValue1()));
                }
            }
        }
    }

    public static boolean isWrapClass(Class<?> clazz) {
        return wrapClassToPrimitive.get(clazz) != null;
    }

    /**
     * @param fromClass
     * @param toClass
     * @return
     */
    @SuppressWarnings("all")
    public static <FROM, TO> Converter<FROM, TO> getConverter(Class<FROM> fromClass, Class<TO> toClass) {
        return (Converter<FROM, TO>) converters.get(fromClass, toClass);
    }

    /**
     * 覆盖本类转换，并递归添加所有不存在的父类转换以及接口转换器
     * 
     * @param <FROM>
     * @param <TO>
     * @param converter
     * @param fromClass
     * @param toClass
     */
    public static <FROM, TO> void addConverter(
            Converter<FROM, TO> converter,
            Class<FROM> fromClass,
            Class<TO> toClass) {
        converters.put(fromClass, toClass, converter);
        addConverterNotExists(converter, fromClass, toClass.getSuperclass());
        for (Class<?> toInterface : toClass.getInterfaces())
            addConverterNotExists(converter, fromClass, toInterface);
    }

    private static void addConverterNotExists(Converter<?, ?> converter, Class<?> fromClass, Class<?> toClass) {
        if (fromClass == null || toClass == null || converters.containsKey(fromClass, toClass))
            return;
        converters.put(fromClass, toClass, converter);
        addConverterNotExists(converter, fromClass, toClass.getSuperclass());
        for (Class<?> toInterface : toClass.getInterfaces())
            addConverterNotExists(converter, fromClass, toInterface);
    }

    public static <FROM, TO> boolean canConvert(Class<FROM> fromClass, Class<TO> toClass) {
        return getConverter(fromClass, toClass) != null;
    }

    /**
     * 转换类型
     * 
     * @param <FROM>
     * @param <TO>
     * @param value
     * @param toClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <FROM, TO> TO convert(FROM value, Class<TO> toClass) {
        // 转为包装类
        if (toClass.isPrimitive())
            toClass = (Class<TO>) primitiveClassToWrap.get(toClass);
        // 可直接指定，则返回value
        if (toClass.isAssignableFrom(value.getClass()))
            return (TO) value;
        // 查询转换器
        Converter<FROM, TO> converter = (Converter<FROM, TO>) getConverter(value.getClass(), toClass);
        if (converter == null)
            return null;
        return converter.convert(value);
    }
}
