package com.bknife.base.util.type;

import java.lang.reflect.Array;

import com.bknife.base.util.ResolveType;

public class SpecialArrayResolveType implements ResolveType {
    private Class<?> type;
    private Class<?> componentType;

    public SpecialArrayResolveType(Class<?> type) {
        this.type = type;
        componentType = type.getComponentType();
    }

    @Override
    public Object newInstance(Object... args) throws Exception {
        if (args.length != 0 || (args[0].getClass() != int.class && args[0] instanceof Integer))
            throw new IllegalArgumentException("arguments mismatch");

        return Array.newInstance(componentType, (int) args[0]);
    }

    @Override
    public Class<?> getTypeClass() {
        return type;
    }

    @Override
    public int getGenericCount() {
        return 1;
    }

    @Override
    public ResolveType getGenericType(int index) {
        if (index != 0)
            throw new IndexOutOfBoundsException("index out of bounds: " + index);
        return ResolveType.resolve(componentType);
    }

    @Override
    public Class<?> getGenericClass(int index) {
        if (index != 0)
            throw new IndexOutOfBoundsException("index out of bounds: " + index);
        return componentType;
    }

}
