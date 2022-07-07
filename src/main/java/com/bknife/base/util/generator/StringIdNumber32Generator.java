package com.bknife.base.util.generator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class StringIdNumber32Generator implements StringIdGenerator {
    private static final int SEQUENCE_MAX = 9999999;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private int sequence = 0;
    private long generateTime = 0;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public String nextId() throws Exception {
        long currentTime = System.currentTimeMillis();
        int sequenceValue;
        synchronized (this) {
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

        int salt1 = Math.abs(random.nextInt()) % 9999;
        int salt2 = Math.abs(random.nextInt()) % 9999;
        return dateFormat.format(new Date(currentTime)) +
                Strings.padStart(Integer.toString(sequenceValue), 7, '0') +
                Strings.padStart(Integer.toString(salt1), 4, '0') +
                Strings.padStart(Integer.toString(salt2), 4, '0');
    }

    private long waitUtilNextMS() {
        long currentTime;
        do {
            currentTime = System.currentTimeMillis();
        } while (currentTime == generateTime);
        return currentTime;
    }
}
