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
        Constructor<?> constructor = getTypeClass().getConstructor(ResolveType.getArgumentTypes(args));
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
        ParameterizedResolveType other = (ParameterizedResolveType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParameterizedResolveType [type=" + type + "]";
    }
}
