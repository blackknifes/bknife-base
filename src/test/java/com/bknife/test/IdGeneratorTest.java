package com.bknife.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.bknife.base.util.Ids;
import com.bknife.base.util.LongIdGenerator;
import com.bknife.base.util.generator.PearFlowerGenerator;

public class IdGeneratorTest {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void testSnowFlake() throws Exception {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = LongIdGenerator.getSnowFlake(
                dateFormat.parse("2022-05-05").getTime(), 0, 0);

        for (int i = 0; i < 1000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            System.out.println("SnowFlake id: " + id);
            values.add(id);
        }
    }

    @Test
    public void testPearFlower() throws Exception
    {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = new PearFlowerGenerator(dateFormat.parse("2022-05-05").getTime(), 0, 0);
        for (int i = 0; i < 100000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            System.out.println("PearFlower id: " + id);
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
