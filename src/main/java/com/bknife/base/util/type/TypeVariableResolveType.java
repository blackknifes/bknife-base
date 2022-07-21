package com.bknife.base.util.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import com.bknife.base.util.ResolveType;

public class TypeVariableResolveType implements ResolveType {

    private TypeVariable<?> type;

    public TypeVariableResolveType(TypeVariable<?> type) {
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
        return ResolveType.getTypeClass((Type) type.getGenericDeclaration());
    }

    @Override
    public int getGenericCount() {
        return type.getBounds().length;
    }

    @Override
    public ResolveType getGenericType(int index) {
        return ResolveType.resolve(type.getBounds()[index]);
    }

    @Override
    public Class<?> getGenericClass(int index) {
        return ResolveType.getTypeClass(type.getBounds()[index]);
    }

}
