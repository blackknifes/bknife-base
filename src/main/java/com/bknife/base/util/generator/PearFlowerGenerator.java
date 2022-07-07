package com.bknife.base.util.generator;

import com.bknife.base.util.LongIdGenerator;

public class PearFlowerGenerator implements LongIdGenerator {

    private static final long SEQUENCE_MASK = 0x7FFFl;
    private static final long SEGMENT_MASK = 0x7l;
    private static final long MACHINE_ID_MASK = 0x3FFl;
    private static final long TIMESTAMP_MASK = 0x7FFFFFFFl;

    private static final long MACHINE_ID_OFFSET = 19;
    private static final long SEGMENT_OFFSET = MACHINE_ID_OFFSET + 10;
    private static final long TIMESTAMP_OFFSET = SEGMENT_OFFSET + 3;

    private long serverTime;
    private long segment;
    private long machineId;

    private long generateTime = 0;
    private long sequence = 0;

    public PearFlowerGenerator(long serverTime, int segment, int machineId) {
        long currentTime = System.currentTimeMillis();
        if (segment > SEQUENCE_MASK)
            throw new IllegalArgumentException("segment is too large: " + segment + " > " + SEGMENT_MASK);
        if (machineId > MACHINE_ID_MASK)
            throw new IllegalArgumentException("machine id is too large: " + machineId + " > " + MACHINE_ID_MASK);
        if (serverTime > currentTime)
            throw new IllegalArgumentException(
                    "server time cannot greater than current time: " + serverTime + " > " + currentTime);
        this.serverTime = serverTime / 1000;
        this.segment = segment;
        this.machineId = machineId;
        this.generateTime = currentTime / 1000 - this.serverTime;
    }

    @Override
    public long nextId() throws Exception {
        long currentSecond = System.currentTimeMillis() / 1000;
        long timestamp;
        long sequenceValue;
        synchronized (this) {
            // 秒更新，更新时间，并更新序号为0
            if (currentSecond > this.generateTime) {
                this.generateTime = currentSecond;
                sequence = 0;
            } else if (sequence == SEQUENCE_MASK) {// 序号已满，借用下一秒时间
                ++this.generateTime;
                sequence = 0;
            }
            sequenceValue = ++sequence;
            timestamp = (this.generateTime & TIMESTAMP_MASK) << TIMESTAMP_OFFSET;
        }

        long segmentValue = segment << SEGMENT_OFFSET;
        long machineIdValue = machineId << MACHINE_ID_OFFSET;
        sequenceValue = sequenceValue & SEQUENCE_MASK;
        return timestamp | segmentValue | machineIdValue | sequenceValue;
    }
}
