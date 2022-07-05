package com.bknife.base.util.generator;

import com.bknife.base.util.LongIdGenerator;
import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class StringGenerator implements StringIdGenerator {

    private int length;
    private LongIdGenerator longIdGenerator;

    public StringGenerator(int length, LongIdGenerator longIdGenerator) {
        this.length = length;
        this.longIdGenerator = longIdGenerator;
    }

    @Override
    public String nextId() throws Exception {
        return Strings.padStart(String.valueOf(longIdGenerator.nextId()), length, '0');
    }
}
