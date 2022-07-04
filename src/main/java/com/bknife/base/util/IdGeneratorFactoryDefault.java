package com.bknife.base.util;

import com.bknife.base.util.generator.SnowFlakeGenerator;

public class IdGeneratorFactoryDefault implements IdGeneratorFactory {
    private static IdGeneratorFactoryDefault instance;

    public static synchronized IdGeneratorFactoryDefault getFactory() {
        if (instance == null)
            instance = new IdGeneratorFactoryDefault();
        return instance;
    }

    @Override
    public LongIdGenerator getLongIdGenerator(Object... args) {
        if (args.length < 3 || !(args[0] instanceof Number) || !(args[1] instanceof Number)
                || !(args[2] instanceof Number))
            return null;

        return new SnowFlakeGenerator((long) args[0], (int) args[1], (int) args[2]);
    }

    @Override
    public StringIdGenerator getStringIdGenerator(Object... args) {
        return null;
    }

}
