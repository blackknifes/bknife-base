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
        if (args.length != 1 || args[0] == null)
            throw new IllegalArgumentException("arguments count mismatch");
        Class<?> argClass = args[0].getClass();
        int len;
        if (argClass == int.class || Integer.class.isAssignableFrom(argClass))
            len = (int) args[0];
        else
            throw new IllegalArgumentException("arguments count mismatch");

        Class<?> componentClass = ResolveType.getTypeClass(type.getGenericComponentType());
        if (componentClass == null)
            return null;
        return Array.newInstance(componentClass, len);
    }

    @Override
    public Class<?> getTypeClass() {
        Class<?> componentClass = getGenericClass(0);
        if (componentClass == null)
            return null;
        return Array.newInstance(componentClass, 0).getClass();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        ArrayResolveType other = (ArrayResolveType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArrayResolveType [type=" + type + "]";
    }

}
