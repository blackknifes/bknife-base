package com.bknife.base.util.type;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;

import com.bknife.base.util.ResolveType;

public class ArrayResolveType implements ResolveType {

    private GenericArrayType type;

    public ArrayResolveType(GenericArrayType type) {
        this.type = type;
    }

    @Override
    public Object newInstance(Object... args) {
        if (args.length != 1)
            throw new IllegalArgumentException("arguments count mismatch");
        int[] intArgs = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            if (!(args[i] instanceof Integer))
                throw new IllegalArgumentException(i + "th arguments is not a int value");
            intArgs[i] = (Integer) args[i];
        }

        return Array.newInstance(ResolveType.getTypeClass(type.getGenericComponentType()), intArgs);
    }

    @Override
    public Class<?> getTypeClass() {
        return Array.class;
    }

    @Override
    public int getGenericCount() {
        return 1;
    }

    @Override
    public ResolveType getGenericType(int index) {
        if (index > 0 || index < 0)
            throw new IndexOutOfBoundsException("index out of bounds: " + index);
        return ResolveType.resolve(type.getGenericComponentType());
    }

    @Override
    public Class<?> getGenericClass(int index) {
        if (index > 0 || index < 0)
            throw new IndexOutOfBoundsException("index out of bounds: " + index);
        return ResolveType.getTypeClass(type.getGenericComponentType());
    }

}
