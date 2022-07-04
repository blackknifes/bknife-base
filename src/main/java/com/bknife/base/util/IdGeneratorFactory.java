package com.bknife.base.util;

public interface IdGeneratorFactory {
    public static IdGeneratorFactory getDefaultFactory() {
        return IdGeneratorFactoryDefault.getFactory();
    }

    public LongIdGenerator getLongIdGenerator(Object... args);

    public StringIdGenerator getStringIdGenerator(Object... args);
}
