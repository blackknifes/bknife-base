package com.bknife.base.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.bknife.base.util.type.ArrayResolveType;
import com.bknife.base.util.type.ClassResolveType;
import com.bknife.base.util.type.ParameterizedResolveType;

public interface ResolveType {

    public static ResolveType resolve(Type type) {
        if (type instanceof ParameterizedType)
            return new ParameterizedResolveType((ParameterizedType) type);
        else if (type instanceof GenericArrayType)
            return new ArrayResolveType((GenericArrayType) type);
        else if (type instanceof Class)
            return new ClassResolveType((Class<?>) type);
        return null;
    }

    public static Class<?> getTypeClass(Type type) {
        for (;;) {
            if (type == null)
                return null;
            if (type instanceof ParameterizedType)
                type = ((ParameterizedType) type).getRawType();
            else if (type instanceof GenericArrayType)
                type = ((GenericArrayType) type).getGenericComponentType();
            else if (type instanceof Class)
                return (Class<?>) type;
            else
                return null;
        }
    }

    /**
     * 创建一个对象
     * 
     * @param args
     * @return
     * @throws Exception
     */
    public Object newInstance(Object... args) throws Exception;

    /**
     * 获取类
     * 
     * @return
     */
    public Class<?> getTypeClass();

    /**
     * 获取泛型长度
     * 
     * @return
     */
    public int getGenericCount();

    /**
     * 获取泛型类型
     * 
     * @param index
     * @return
     */
    public ResolveType getGenericType(int index);

    /**
     * 获取泛型类
     * 
     * @param index
     * @return
     */
    public Class<?> getGenericClass(int index);
}
