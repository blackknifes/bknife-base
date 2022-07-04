package com.bknife.base.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ResolveType {
    private static enum Kind {
        ClassType,
        ParameterizedType,
        GenericArrayType
    }

    private Type type;
    private Kind kind;

    public static ResolveType resolve(Type type) throws Exception {
        if (type instanceof Class)
            return new ResolveType((Class<?>) type);
        else if (type instanceof ParameterizedType)
            return new ResolveType((ParameterizedType) type);
        else if (type instanceof GenericArrayType)
            return new ResolveType((GenericArrayType) type);
        throw new Exception("type " + type + " is not supported");
    }

    private ResolveType(Type type) {
        this.type = type;
        if (type instanceof Class)
            kind = Kind.ClassType;
        else if (type instanceof ParameterizedType)
            kind = Kind.ParameterizedType;
        else if (type instanceof GenericArrayType)
            kind = Kind.GenericArrayType;
    }

    private ResolveType(Class<?> clazz) {
        type = clazz;
        kind = Kind.ClassType;
    }

    private ResolveType(ParameterizedType parameterizedType) {
        type = parameterizedType;
        kind = Kind.ParameterizedType;
    }

    private ResolveType(GenericArrayType genericArrayType) {
        type = genericArrayType;
        kind = Kind.GenericArrayType;
    }

    public Object newInstance() throws InstantiationException, IllegalAccessException {
        return getType().newInstance();
    }

    public Class<?> getType() {
        switch (kind) {
            case ClassType:
                return (Class<?>) type;
            case ParameterizedType:
                return new ResolveType(((ParameterizedType) type).getRawType()).getType();
            case GenericArrayType:
                return Array.class;
            default:
                break;
        }
        return null;
    }

    public int getGenericCount() {
        switch (kind) {
            case ParameterizedType:
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return parameterizedType.getActualTypeArguments().length;
            case GenericArrayType:
                return 1;
            default:
                break;
        }
        return 0;
    }

    public Class<?> getGenericClass(int index) {
        switch (kind) {
            case ParameterizedType:
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return (Class<?>) parameterizedType.getActualTypeArguments()[index];
            case GenericArrayType:
                if (index >= 1)
                    throw new IndexOutOfBoundsException();
                GenericArrayType genericArrayType = (GenericArrayType) type;
                return (Class<?>) genericArrayType.getGenericComponentType();
            default:
                break;
        }
        throw new IndexOutOfBoundsException();
    }

    public Kind getKind() {
        return kind;
    }
}
