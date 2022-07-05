package com.bknife.base.util.generator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class StringIdNumber16Generator implements StringIdGenerator {
    private int SEQUENCE_MAX = 99;
    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private int sequence = 0;
    private long generateTime = 0;
    private long serverTime;

    public StringIdNumber16Generator(long serverTime) {
        this.serverTime = serverTime;
    }

    @Override
    public String nextId() throws Exception {
        long currentTime;
        int sequenceValue;
        synchronized (this) {
            currentTime = System.currentTimeMillis() - serverTime;
            for (;;) {
                if (currentTime < generateTime)
                    throw new Exception("rewinded clock");

                if (currentTime > generateTime) {// 时间发生变化，重置sequence
                    generateTime = currentTime;
                    sequence = sequenceValue = 1;
                } else {
                    if (sequence == SEQUENCE_MAX) {// sequence达到最大负载，等到下一秒获取sequence
                        currentTime = waitUtilNextMS();
                        sequence = 0;
                        continue;
                    }
                    sequenceValue = ++sequence; // sequence + 1
                }
                break;
            }
        }
        return dateFormat.format(new Date(currentTime)) + Strings.padStart("" + sequenceValue, 2, '0');
    }

    private long waitUtilNextMS() {
        long currentTime;
        do {
            currentTime = System.currentTimeMillis();
        } while (currentTime == generateTime);
        return currentTime - serverTime;
    }
}
