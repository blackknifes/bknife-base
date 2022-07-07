package com.bknife.base.util.generator;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.bknife.base.util.LongIdGenerator;

public class MistGenerator implements LongIdGenerator {
    private static final long INCRESMENT_MASK = 0x7FFFFFFFFFFl;
    private static final long INCRESMENT_OFFSET = 16;
    private static final long SALT_MASK = 0xFF;
    private final Random random = new Random(System.currentTimeMillis());

    private AtomicLong sequence = new AtomicLong();
    private final long initValue;

    public MistGenerator() {
        this.initValue = Math.abs(random.nextLong()) & INCRESMENT_MASK;
        sequence.set(initValue);
    }

    public MistGenerator(long initValue) {
        this.initValue = initValue & INCRESMENT_MASK;
        sequence.set(initValue);
    }

    public MistGenerator(long initValue, long sequence) {
        this.initValue = initValue & INCRESMENT_MASK;
        if (sequence < 0)
            throw new IllegalArgumentException("initValue < 0");
        this.sequence.set(sequence);
    }

    @Override
    public long nextId() throws Exception {

        long sequenceValue = this.sequence.incrementAndGet();
        while (sequenceValue > INCRESMENT_MASK) {
            this.sequence.compareAndSet(sequenceValue, -1);
            sequenceValue = this.sequence.incrementAndGet();
        }
        if (sequenceValue == initValue)
            throw new Exception("id has not more value");

        long salt1 = Math.abs(random.nextInt()) & SALT_MASK;
        long salt2 = Math.abs(random.nextInt()) & SALT_MASK;
        return ((sequenceValue & INCRESMENT_MASK) << INCRESMENT_OFFSET) | (salt1 << 1) | salt2;
    }

    public long getInitValue() {
        return initValue;
    }

    public long getSequence() {
        return sequence.get();
    }
}
