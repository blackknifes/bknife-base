package com.bknife.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.bknife.base.util.Ids;
import com.bknife.base.util.LongIdGenerator;
import com.bknife.base.util.generator.MistGenerator;
import com.bknife.base.util.generator.PearFlowerGenerator;
import com.bknife.base.util.generator.SnowFlakeGenerator;

public class IdGeneratorTest {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testSnowFlake() throws Exception {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = new SnowFlakeGenerator(dateFormat.parse("2022-05-05").getTime(), 0, 0);

        for (int i = 0; i < 1000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            values.add(id);
        }
    }

    @Test
    public void testPearFlower() throws Exception {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = new PearFlowerGenerator(dateFormat.parse("2022-05-05").getTime(), 0, 0);
        for (int i = 0; i < 100000; i++) {
            long id = generator.nextId();
            Assert.assertFalse(values.contains(id));
            values.add(id);
        }
    }

    @Test
    public void testMist() throws Exception {
        Set<Long> values = new HashSet<Long>();
        LongIdGenerator generator = new MistGenerator();
        for (int i = 0; i < 100000; i++) {
            long id = generator.nextId();
            // System.out.println("Mist id: " + Long.toHexString(id));
            Assert.assertFalse(values.contains(id));
            values.add(id);
        }
    }

    @Test
    public void testIdsNextLongId() throws Exception {
        Set<Long> values = new HashSet<Long>();

        for (int i = 0; i < 1000; i++) {
            long id = Ids.nextLongId();
            Assert.assertFalse(values.contains(id));
            values.add(id);
        }
    }

    @Test
    public void testIdsNextStringId32() throws Exception {
        Set<String> values = new HashSet<String>();

        for (int i = 0; i < 1000; i++) {
            String id = Ids.nextStringId32();
            Assert.assertEquals(id.length(), 32);
            Assert.assertFalse(values.contains(id));
            values.add(id);
        }
    }
}
