package com.bknife.base.util.generator;

import java.util.UUID;

import com.bknife.base.util.StringIdGenerator;

public class StringIdUUIDGenerator implements StringIdGenerator {

    @Override
    public String nextId() throws Exception {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
