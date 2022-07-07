package com.bknife.base.util;

import com.bknife.base.util.generator.LongHexStringIdGenerator;
import com.bknife.base.util.generator.LongNumberStringIdGenerator;
import com.bknife.base.util.generator.MistGenerator;
import com.bknife.base.util.generator.PearFlowerGenerator;
import com.bknife.base.util.generator.SnowFlakeGenerator;
import com.bknife.base.util.generator.StringIdNumber32Generator;
import com.bknife.base.util.generator.StringIdUUIDGenerator;

public abstract class IdGeneratorFactory {
    public static SnowFlakeGenerator getSnowFlake(long serverTime, int dataCenterId, int workerId) {
        return new SnowFlakeGenerator(serverTime, dataCenterId, workerId);
    }

    public static PearFlowerGenerator getPearFlower(long serverTime, int segment, int machineId) {
        return new PearFlowerGenerator(serverTime, segment, machineId);
    }

    public static MistGenerator getMist(long initValue, long sequence) {
        return new MistGenerator(initValue, sequence);
    }

    public static StringIdNumber32Generator getNumber32String() {
        return new StringIdNumber32Generator();
    }

    public static StringIdUUIDGenerator getUUID() {
        return new StringIdUUIDGenerator();
    }

    public static LongHexStringIdGenerator getLongHex(LongIdGenerator generator) {
        return new LongHexStringIdGenerator(generator);
    }

    public static LongNumberStringIdGenerator getLongNumber(LongIdGenerator generator) {
        return new LongNumberStringIdGenerator(generator);
    }

    public static LongHexStringIdGenerator getLongHex(LongIdGenerator generator, int length) {
        return new LongHexStringIdGenerator(generator, length);
    }

    public static LongNumberStringIdGenerator getLongNumber(LongIdGenerator generator, int length) {
        return new LongNumberStringIdGenerator(generator, length);
    }
}
