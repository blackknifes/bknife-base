package com.bknife.base.util;

public interface LongIdGenerator {
    public static final long TIME_BACK = -1l;
    public long nextId() throws Exception;
}
