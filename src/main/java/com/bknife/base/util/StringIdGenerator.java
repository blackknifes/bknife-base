package com.bknife.base.util;

import com.bknife.base.util.generator.StringIdNumber16Generator;
import com.bknife.base.util.generator.StringIdSnowFlakeGenerator;
import com.bknife.base.util.generator.StringIdUUIDGenerator;

public interface StringIdGenerator {
    public static StringIdGenerator getUUID() {
        return new StringIdUUIDGenerator();
    }

    public static StringIdGenerator getNumber16(long serverTime) {
        return new StringIdNumber16Generator(serverTime);
    }

    public static StringIdGenerator getString32(long serverTime, int dataCenterId, int workerId) {
        return new StringIdSnowFlakeGenerator(serverTime, dataCenterId, workerId);
    }

    public String nextId() throws Exception;
}
