package com.bknife.base.util.generator;

import com.bknife.base.util.LongIdGenerator;
import com.bknife.base.util.StringIdGenerator;
import com.bknife.base.util.Strings;

public class LongHexStringIdGenerator implements StringIdGenerator{
    private LongIdGenerator longIdGenerator;
    private int length;

    public LongHexStringIdGenerator(LongIdGenerator longIdGenerator, int length) {
        this.longIdGenerator = longIdGenerator;
        this.length = length;
    }

    public LongHexStringIdGenerator(LongIdGenerator longIdGenerator) {
        this.longIdGenerator = longIdGenerator;
        this.length = 16;
    }

    @Override
    public String nextId() throws Exception {
        return Strings.padStart(Long.toHexString(longIdGenerator.nextId()), length, '0');
    }
    
}
