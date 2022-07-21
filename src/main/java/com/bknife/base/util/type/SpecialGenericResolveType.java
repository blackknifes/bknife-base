package com.bknife.base.util.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import com.bknife.base.util.ResolveType;

public class SpecialGenericResolveType implements ResolveType {
    private Class<?> type;
    private Type[] generics;

    public SpecialGenericResolveType(Type type, Type[] generics) {
        this.type = ResolveType.getTypeClass(type);
        this.generics = generics;
    }

    @Override
    public Object newInstance(Object... args) throws Exception {
        Constructor<?> constructor = getTypeClass().getConstructor(ResolveType.getArgumentTypes(args));
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    @Override
    public Class<?> getTypeClass() {
        return type;
    }

    @Override
    public int getGenericCount() {
        return generics.length;
    }

    @Override
    public ResolveType getGenericType(int index) {
        return ResolveType.resolve(generics[index]);
    }

    @Override
    public Class<?> getGenericClass(int index) {
        return ResolveType.getTypeClass(generics[index]);
    }

}
