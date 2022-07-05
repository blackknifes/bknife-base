package com.bknife.base.util;

import com.bknife.base.util.generator.SnowFlakeGenerator;

public interface LongIdGenerator {
    public static LongIdGenerator getSnowFlake(long serverTime, int dataCenterId, int workerId) {
        return new SnowFlakeGenerator(serverTime, dataCenterId, workerId);
    }

    public long nextId() throws Exception;
}
