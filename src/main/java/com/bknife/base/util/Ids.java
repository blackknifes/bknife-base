package com.bknife.base.util;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Ids {

    private static long getServerTime() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long val = dateFormat.parse("2022-07-05").getTime();
            return val;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1656950400000l;
    }

    private static int getWorkerId() {
        return Math.abs(ManagementFactory.getRuntimeMXBean().getName().hashCode()) % 32;
    }

    private static int getDataCenterId() {
        return (int) Math.abs(Thread.currentThread().getId()) % 32;
    }

    private static final long SERVER_TIME = getServerTime();

    private static LongIdGenerator longIdGenerator = LongIdGenerator.getSnowFlake(SERVER_TIME, getDataCenterId(),
            getWorkerId());
    private static StringIdGenerator stringIdGenerator16 = StringIdGenerator.getNumber16(SERVER_TIME);
    private static StringIdGenerator stringIdGenerator32 = StringIdGenerator.getString32(SERVER_TIME, getDataCenterId(),
            getWorkerId());

    public static long nextLongId() throws Exception {
        return longIdGenerator.nextId();
    }

    public static String nextStringId16() throws Exception {
        return stringIdGenerator16.nextId();
    }

    public static String nextStringId32() throws Exception {
        return stringIdGenerator32.nextId();
    }
}
