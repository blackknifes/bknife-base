package com.bknife.base.util.generator;

import com.bknife.base.util.LongIdGenerator;
import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class LongNumberStringIdGenerator implements StringIdGenerator{
    private LongIdGenerator longIdGenerator;
    private int length;

    public LongNumberStringIdGenerator(LongIdGenerator longIdGenerator, int length) {
        this.longIdGenerator = longIdGenerator;
        this.length = length;
    }

    public LongNumberStringIdGenerator(LongIdGenerator longIdGenerator) {
        this.longIdGenerator = longIdGenerator;
        this.length = 19;
    }

    @Override
    public String nextId() throws Exception {
        
        return Strings.padStart(Long.toString(longIdGenerator.nextId()), length, '0');
    }
}
