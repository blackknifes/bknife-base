package com.bknife.base.json.deserializer;

public interface JsonVisitor {
    default boolean visitObjectBegin() {
        return true;
    }

    default boolean visitObjectEnd() {
        return true;
    }

    default boolean visitArrayBegin() {
        return true;
    }

    default boolean visitArrayEnd() {
        return true;
    }

    default boolean visitKey(String key) {
        return true;
    }

    default boolean visitValue(Object value) {
        return true;
    }
}
