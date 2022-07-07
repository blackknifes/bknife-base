package com.bknife.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 唯一id生成工具类
 */
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

    private static final long SERVER_TIME = getServerTime();

    private static LongIdGenerator longIdGenerator = IdGeneratorFactory.getPearFlower(SERVER_TIME, 1, 1);
    private static StringIdGenerator stringIdGenerator32 = IdGeneratorFactory.getNumber32String();

    public static long nextLongId() throws Exception {
        return longIdGenerator.nextId();
    }

    public static String nextStringId32() throws Exception {
        return stringIdGenerator32.nextId();
    }
}
