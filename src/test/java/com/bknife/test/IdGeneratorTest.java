package com.bknife.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.bknife.base.util.IdGeneratorFactory;
import com.bknife.base.util.LongIdGenerator;

public class IdGeneratorTest {
    @Test
    public void testLongIdGenerator() throws Exception {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = IdGeneratorFactory.getDefaultFactory()
                .getLongIdGenerator(System.currentTimeMillis(), 0, 0);

        for (int i = 0; i < 1000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            System.out.println("long id: " + id);
            values.add(id);
        }
    }
}
