package com.bknife.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.bknife.base.util.Ids;
import com.bknife.base.util.LongIdGenerator;

public class IdGeneratorTest {
    @Test
    public void testLongIdGenerator() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = LongIdGenerator.getSnowFlake(
                dateFormat.parse("2022-05-05").getTime(), 0, 0);

        for (int i = 0; i < 1000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            System.out.println("long id: " + id);
            values.add(id);
        }
    }

    @Test
    public void testIds() throws Exception {
        Set<Long> values = new HashSet<Long>();

        for (int i = 0; i < 1000; i++) {
            long id = Ids.nextLongId();
            Assert.assertFalse(values.contains(id));
            System.out.println("long id: " + id);
            values.add(id);
        }
    }
}
