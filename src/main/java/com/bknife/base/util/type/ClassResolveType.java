package com.bknife.base.util.type;

import java.lang.reflect.Constructor;

import com.bknife.base.util.ResolveType;

public class ClassResolveType implements ResolveType {
    private Class<?> type;

    public ClassResolveType(Class<?> type) {
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
        return type;
    }

    @Override
    public int getGenericCount() {
        return 0;
    }

    @Override
    public ResolveType getGenericType(int index) {
        return null;
    }

    @Override
    public Class<?> getGenericClass(int index) {
        return null;
    }

}
