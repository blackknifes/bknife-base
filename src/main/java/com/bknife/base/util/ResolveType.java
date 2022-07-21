package com.bknife.base.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import com.bknife.base.util.type.ArrayResolveType;
import com.bknife.base.util.type.ClassResolveType;
import com.bknife.base.util.type.ParameterizedResolveType;
import com.bknife.base.util.type.SpecialArrayResolveType;
import com.bknife.base.util.type.SpecialGenericResolveType;
import com.bknife.base.util.type.TypeVariableResolveType;

public interface ResolveType {

    public static Class<?>[] getArgumentTypes(Object... args) {
        Class<?>[] classList = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null)
                classList[i] = null;
            else
                classList[i] = args[i].getClass();
        }
        return classList;
    }

    /**
     * 从field创建ResolveType
     * 
     * @param field
     * @return
     */
    public static ResolveType resolveField(Field field) {
        return resolve(field.getGenericType());
    }

    /**
     * 自定义泛型对象
     * 
     * @param type
     * @param genericTypes
     * @return
     */
    public static ResolveType resolveGeneric(Type type, Type... genericTypes) {
        return new SpecialGenericResolveType(type, genericTypes);
    }

    /**
     * 从数组组件类型创建ResolveType
     * 
     * @param componentType
     * @return
     */
    public static ResolveType resolveArray(Class<?> componentType) {
        return new SpecialArrayResolveType(Array.newInstance(componentType, 0).getClass());
    }

    /**
     * 接收一个type类型创建ResolveType
     */
    public static ResolveType resolve(Type type) {
        if (type instanceof ParameterizedType)
            return new ParameterizedResolveType((ParameterizedType) type);
        else if (type instanceof GenericArrayType)
            return new ArrayResolveType((GenericArrayType) type);
        else if (type instanceof TypeVariable)
            return new TypeVariableResolveType((TypeVariable<?>) type);
        else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isArray())
                return new SpecialArrayResolveType(clazz);
            return new ClassResolveType((Class<?>) type);
        }
        return null;
    }

    /**
     * 递归查询type的class对象
     * 
     * @param type
     * @return
     */
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
