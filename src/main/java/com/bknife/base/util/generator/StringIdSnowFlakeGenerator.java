package com.bknife.base.util.generator;

import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class StringIdSnowFlakeGenerator implements StringIdGenerator {
    public SnowFlakeGenerator generator;

    public StringIdSnowFlakeGenerator(long serverTime, int dataCenterId, int workerId) {
        generator = new SnowFlakeGenerator(serverTime, dataCenterId, workerId);
    }

    @Override
    public String nextId() throws Exception {
        return Strings.padStart(String.valueOf(generator.nextId()), 32, '0');
    }
}
