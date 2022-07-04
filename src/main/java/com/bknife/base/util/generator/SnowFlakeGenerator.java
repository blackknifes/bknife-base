package com.bknife.base.util.generator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bknife.base.util.LongIdGenerator;

public class SnowFlakeGenerator implements LongIdGenerator {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final long SEQUENCE_MASK = 0xFFFl;
    private static final long WORKER_ID_OFFSET = 12;
    private static final long DATACENTER_ID_OFFSET = 17;
    private static final long TIMESTAMP_MASK = 0x7FFFFFFFFFC00000l;
    private static final long TIMESTAMP_OFFSET = 22;

    private long serverTime;
    private long dataCenterId;
    private long workerId;
    private long generateTime = 0;
    private long sequence = 0;

    public SnowFlakeGenerator(long serverTime, int dataCenterId, int workerId) {
        this.serverTime = serverTime;
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
        if (dataCenterId > 31)
            throw new IllegalArgumentException("data center id cannot be greater than 31");
        if (workerId > 31)
            throw new IllegalArgumentException("worker id cannot be greater than 31");
    }

    public SnowFlakeGenerator(String datetime, int dataCenterId, int workerId) throws ParseException {
        this.serverTime = dateFormat.parse(datetime).getTime();
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
        if (dataCenterId > 31)
            throw new IllegalArgumentException("data center id cannot be greater than 31");
        if (workerId > 31)
            throw new IllegalArgumentException("worker id cannot be greater than 31");
    }

    @Override
    public long nextId() throws Exception {
        long currentTime;
        long sequenceValue;
        synchronized (this) {
            currentTime = System.currentTimeMillis() - serverTime;
            for (;;) {
                if (currentTime < generateTime)
                    throw new Exception("rewinded clock");

                if (currentTime > generateTime) {// 时间发生变化，重置sequence
                    generateTime = currentTime;
                    sequence = sequenceValue = 1;
                } else {
                    if (sequence == SEQUENCE_MASK) {// sequence达到最大负载，等到下一秒获取sequence
                        currentTime = waitUtilNextMS();
                        sequence = 0;
                        continue;
                    }
                    sequenceValue = ++sequence; // sequence + 1
                }
                break;
            }
        }
        return sequenceValue | (workerId << WORKER_ID_OFFSET) | (dataCenterId << DATACENTER_ID_OFFSET)
                | ((currentTime << TIMESTAMP_OFFSET) & TIMESTAMP_MASK);
    }

    private long waitUtilNextMS() {
        long currentTime;
        do {
            currentTime = System.currentTimeMillis();
        } while (currentTime == generateTime);
        return currentTime - serverTime;
    }
}
