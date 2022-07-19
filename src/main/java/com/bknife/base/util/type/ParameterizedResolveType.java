package com.bknife.base.util.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import com.bknife.base.util.ResolveType;

public class ParameterizedResolveType implements ResolveType {
    private ParameterizedType type;

    public ParameterizedResolveType(ParameterizedType type) {
        this.type = type;
    }

    @Override
    public Object newInstance(Object... args) throws Exception {
        Class<?>[] argClass = new Class<?>[args.length];
        for (int i = 0; i < argClass.length; i++)
            argClass[i] = args[i].getClass();
        Constructor<?> constructor = getTypeClass().getDeclaredConstructor(argClass);
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    @Override
    public Class<?> getTypeClass() {
        return ResolveType.getTypeClass(type);
    }

    @Override
    public int getGenericCount() {
        return type.getActualTypeArguments().length;
    }

    @Override
    public ResolveType getGenericType(int index) {
        return ResolveType.resolve(type.getActualTypeArguments()[index]);
    }

    @Override
    public Class<?> getGenericClass(int index) {
        return ResolveType.getTypeClass(type.getActualTypeArguments()[index]);
    }

}
