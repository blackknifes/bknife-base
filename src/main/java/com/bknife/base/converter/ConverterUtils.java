package com.bknife.base.converter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bknife.base.util.ClassUtils;
import com.bknife.base.util.ResolveType;

public class ConverterUtils {
    private static class ConverterKey {
        private Class<?> fromClass;
        private Class<?> toClass;

        public ConverterKey(Class<?> fromClass, Class<?> toClass) {
            this.fromClass = fromClass;
            this.toClass = toClass;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((fromClass == null) ? 0 : fromClass.hashCode());
            result = prime * result + ((toClass == null) ? 0 : toClass.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ConverterKey other = (ConverterKey) obj;
            if (fromClass == null) {
                if (other.fromClass != null)
                    return false;
            } else if (!fromClass.equals(other.fromClass))
                return false;
            if (toClass == null) {
                if (other.toClass != null)
                    return false;
            } else if (!toClass.equals(other.toClass))
                return false;
            return true;
        }
    }

    private static Map<Class<?>, Class<?>> wrapClassToPrimitive = new HashMap<Class<?>, Class<?>>();
    private static Map<Class<?>, Class<?>> primitiveClassToWrap = new HashMap<Class<?>, Class<?>>();
    private static Map<ConverterKey, Converter<?, ?>> converters = new HashMap<ConverterKey, Converter<?, ?>>();

    static {
        init();
    }

    @SuppressWarnings("all")
    private static void init() {
        wrapClassToPrimitive.put(Boolean.class, boolean.class);
        wrapClassToPrimitive.put(Byte.class, byte.class);
        wrapClassToPrimitive.put(Character.class, char.class);
        wrapClassToPrimitive.put(Short.class, short.class);
        wrapClassToPrimitive.put(Integer.class, int.class);
        wrapClassToPrimitive.put(Long.class, long.class);
        wrapClassToPrimitive.put(Float.class, float.class);
        wrapClassToPrimitive.put(Double.class, double.class);

        primitiveClassToWrap.put(boolean.class, Boolean.class);
        primitiveClassToWrap.put(byte.class, Byte.class);
        primitiveClassToWrap.put(char.class, Character.class);
        primitiveClassToWrap.put(short.class, Short.class);
        primitiveClassToWrap.put(int.class, Integer.class);
        primitiveClassToWrap.put(long.class, Long.class);
        primitiveClassToWrap.put(float.class, Float.class);
        primitiveClassToWrap.put(double.class, Double.class);

        try {
            Collection<Class<?>> classList = ClassUtils.scanClasses(
                    ConverterUtils.class.getPackage().getName() + ".impl",
                    Converter.class);

            for (Class<?> clazz : classList) {
                if (clazz == Converter.class)
                    continue;
                try {
                    for (Type interfaceType : clazz.getGenericInterfaces()) {
                        ResolveType type = ResolveType.resolve(interfaceType);
                        Class<?> fromClass = type.getGenericClass(0);
                        Class<?> toClass = type.getGenericClass(1);
                        addConverter((Converter) clazz.newInstance(), fromClass, toClass);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return (Converter<FROM, TO>) getConverter(fromClass, toClass, true);
    }

    @SuppressWarnings("all")
    private static Converter<?, ?> getConverter(Class<?> fromClass, Class<?> toClass, boolean searchNumberProxy) {
        // 查询直接转换器
        Converter<?, ?> convert = converters.get(new ConverterKey(fromClass, toClass));
        if (convert != null)
            return convert;

        // 递归查询from父类转换器
        Class<?> findClass = fromClass;
        while ((findClass = findClass.getSuperclass()) != null) {
            convert = converters.get(new ConverterKey(findClass, toClass));
            if (convert != null)
                return convert;
        }

        // 递归查询接口转换器
        for (Class<?> interfaceClass : fromClass.getInterfaces()) {
            convert = getConverter(interfaceClass, toClass);
            if (convert != null)
                return convert;
        }

        if (!searchNumberProxy)
            return null;

        // 查询以Number为中介的转换器
        Converter<?, ?> toNumberConverter = getConverter(fromClass, Number.class, false);
        if (toNumberConverter == null)
            return null;
        Converter<?, ?> fromNumberConverter = getConverter(Number.class, toClass, false);
        if (fromNumberConverter == null)
            return null;

        return new ConverterNumberProxy(toNumberConverter, fromNumberConverter);
    }

    public static <FROM, TO> void addConverter(
            Converter<FROM, TO> converter,
            Class<FROM> fromClass,
            Class<TO> toClass) {
        converters.put(new ConverterKey(fromClass, toClass), converter);
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
        if (toClass.isAssignableFrom(value.getClass()))
            return (TO) value;

        if (toClass.isPrimitive())
            toClass = (Class<TO>) primitiveClassToWrap.get(toClass);
        Converter<FROM, TO> converter = (Converter<FROM, TO>) getConverter(value.getClass(), toClass);
        if (converter == null)
            return null;
        return converter.convert(value);
    }
}
