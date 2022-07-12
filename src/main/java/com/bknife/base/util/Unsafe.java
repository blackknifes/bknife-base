package com.bknife.base.util;

import java.lang.reflect.Field;


@SuppressWarnings("all")
public final class Unsafe {
    private static sun.misc.Unsafe getUnsafe() {
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (sun.misc.Unsafe) field.get(null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // 单例对象
    private static final sun.misc.Unsafe unsafe = getUnsafe();

    public static final int INVALID_FIELD_OFFSET = sun.misc.Unsafe.INVALID_FIELD_OFFSET;
    public static final int ARRAY_BOOLEAN_BASE_OFFSET = sun.misc.Unsafe.ARRAY_BOOLEAN_BASE_OFFSET;
    public static final int ARRAY_BYTE_BASE_OFFSET = sun.misc.Unsafe.ARRAY_BYTE_BASE_OFFSET;
    public static final int ARRAY_SHORT_BASE_OFFSET = sun.misc.Unsafe.ARRAY_SHORT_BASE_OFFSET;
    public static final int ARRAY_CHAR_BASE_OFFSET = sun.misc.Unsafe.ARRAY_CHAR_BASE_OFFSET;
    public static final int ARRAY_INT_BASE_OFFSET = sun.misc.Unsafe.ARRAY_INT_BASE_OFFSET;
    public static final int ARRAY_LONG_BASE_OFFSET = sun.misc.Unsafe.ARRAY_LONG_BASE_OFFSET;
    public static final int ARRAY_FLOAT_BASE_OFFSET = sun.misc.Unsafe.ARRAY_FLOAT_BASE_OFFSET;
    public static final int ARRAY_DOUBLE_BASE_OFFSET = sun.misc.Unsafe.ARRAY_DOUBLE_BASE_OFFSET;
    public static final int ARRAY_OBJECT_BASE_OFFSET = sun.misc.Unsafe.ARRAY_OBJECT_BASE_OFFSET;
    public static final int ARRAY_BOOLEAN_INDEX_SCALE = sun.misc.Unsafe.ARRAY_BOOLEAN_INDEX_SCALE;
    public static final int ARRAY_BYTE_INDEX_SCALE = sun.misc.Unsafe.ARRAY_BYTE_INDEX_SCALE;
    public static final int ARRAY_SHORT_INDEX_SCALE = sun.misc.Unsafe.ARRAY_SHORT_INDEX_SCALE;
    public static final int ARRAY_CHAR_INDEX_SCALE = sun.misc.Unsafe.ARRAY_CHAR_INDEX_SCALE;
    public static final int ARRAY_INT_INDEX_SCALE = sun.misc.Unsafe.ARRAY_INT_INDEX_SCALE;
    public static final int ARRAY_LONG_INDEX_SCALE = sun.misc.Unsafe.ARRAY_LONG_INDEX_SCALE;
    public static final int ARRAY_FLOAT_INDEX_SCALE = sun.misc.Unsafe.ARRAY_FLOAT_INDEX_SCALE;
    public static final int ARRAY_DOUBLE_INDEX_SCALE = sun.misc.Unsafe.ARRAY_DOUBLE_INDEX_SCALE;
    public static final int ARRAY_OBJECT_INDEX_SCALE = sun.misc.Unsafe.ARRAY_OBJECT_INDEX_SCALE;
    public static final int ADDRESS_SIZE = sun.misc.Unsafe.ADDRESS_SIZE;

    public static int getInt(Object var1, long var2) {
        return unsafe.getInt(var1, var2);
    }

    public static void putInt(Object var1, long var2, int var4) {
        unsafe.putInt(var1, var2, var4);
    }

    public static Object getObject(Object var1, long var2) {
        return unsafe.getObject(var1, var2);
    }

    public static void putObject(Object var1, long var2, Object var4) {
        unsafe.putObject(var1, var2, var4);
    }

    public static boolean getBoolean(Object var1, long var2) {
        return unsafe.getBoolean(var1, var2);
    }

    public static void putBoolean(Object var1, long var2, boolean var4) {
        unsafe.putBoolean(var1, var2, var4);
    }

    public static byte getByte(Object var1, long var2) {
        return unsafe.getByte(var1, var2);
    }

    public static void putByte(Object var1, long var2, byte var4) {
        unsafe.putByte(var1, var2, var4);
    }

    public static short getShort(Object var1, long var2) {
        return unsafe.getShort(var1, var2);
    }

    public static void putShort(Object var1, long var2, short var4) {
        unsafe.putShort(var1, var2, var4);
    }

    public static char getChar(Object var1, long var2) {
        return unsafe.getChar(var1, var2);
    }

    public static void putChar(Object var1, long var2, char var4) {
        unsafe.putChar(var1, var2, var4);
    }

    public static long getLong(Object var1, long var2) {
        return unsafe.getLong(var1, var2);
    }

    public static void putLong(Object var1, long var2, long var4) {
        unsafe.putLong(var1, var2, var4);
    }

    public static float getFloat(Object var1, long var2) {
        return unsafe.getFloat(var1, var2);
    }

    public static void putFloat(Object var1, long var2, float var4) {
        unsafe.putFloat(var1, var2, var4);
    }

    public static double getDouble(Object var1, long var2) {
        return unsafe.getDouble(var1, var2);
    }

    public static void putDouble(Object var1, long var2, double var4) {
        unsafe.putDouble(var1, var2, var4);
    }

    public static byte getByte(long var1) {
        return unsafe.getByte(var1);
    }

    public static void putByte(long var1, byte var3) {
        unsafe.putByte(var1, var3);
    }

    public static short getShort(long var1) {
        return unsafe.getShort(var1);
    }

    public static void putShort(long var1, short var3) {
        unsafe.putShort(var1, var3);
    }

    public static char getChar(long var1) {
        return unsafe.getChar(var1);
    }

    public static void putChar(long var1, char var3) {
        unsafe.putChar(var1, var3);
    }

    public static int getInt(long var1) {
        return unsafe.getInt(var1);
    }

    public static void putInt(long var1, int var3) {
        unsafe.putInt(var1, var3);
    }

    public static long getLong(long var1) {
        return unsafe.getLong(var1);
    }

    public static void putLong(long var1, long var3) {
        unsafe.putLong(var1, var3);
    }

    public static float getFloat(long var1) {
        return unsafe.getFloat(var1);
    }

    public static void putFloat(long var1, float var3) {
        unsafe.putFloat(var1, var3);
    }

    public static double getDouble(long var1) {
        return unsafe.getDouble(var1);
    }

    public static void putDouble(long var1, double var3) {
        unsafe.putDouble(var1, var3);
    }

    public static long getAddress(long var1) {
        return unsafe.getAddress(var1);
    }

    public static void putAddress(long var1, long var3) {
        unsafe.putAddress(var1, var3);
    }

    public static long allocateMemory(long var1) {
        return unsafe.allocateMemory(var1);
    }

    public static long reallocateMemory(long var1, long var3) {
        return unsafe.reallocateMemory(var1, var3);
    }

    public static void setMemory(Object var1, long var2, long var4, byte var6) {
        unsafe.setMemory(var1, var2, var4, var6);
    }

    public static void setMemory(long var1, long var3, byte var5) {
        unsafe.setMemory(var1, var3, var5);
    }

    public static void copyMemory(Object var1, long var2, Object var4, long var5, long var7) {
        unsafe.copyMemory(var1, var2, var4, var5, var7);
    }

    public static void copyMemory(long var1, long var3, long var5) {
        unsafe.copyMemory(var1, var3, var5);
    }

    public static void freeMemory(long var1) {
        unsafe.freeMemory(var1);
    }

    public static long staticFieldOffset(Field var1) {
        return unsafe.staticFieldOffset(var1);
    }

    public static long objectFieldOffset(Field var1) {
        return unsafe.objectFieldOffset(var1);
    }

    public static Object staticFieldBase(Field var1) {
        return unsafe.staticFieldBase(var1);
    }

    public static boolean shouldBeInitialized(Class<?> var1) {
        return unsafe.shouldBeInitialized(var1);
    }

    public static void ensureClassInitialized(Class<?> var1) {
        unsafe.ensureClassInitialized(var1);
    }

    public static int arrayBaseOffset(Class<?> var1) {
        return unsafe.arrayBaseOffset(var1);
    }

    public static int arrayIndexScale(Class<?> var1) {
        return unsafe.arrayIndexScale(var1);
    }

    public static int addressSize() {
        return unsafe.addressSize();
    }

    public static int pageSize() {
        return unsafe.pageSize();
    }

    public static Class<?> defineClass(String var1, byte[] var2, int var3, int var4, ClassLoader var5,
            java.security.ProtectionDomain var6) {
        return unsafe.defineClass(var1, var2, var3, var4, var5, var6);
    }

    public static Class<?> defineAnonymousClass(Class<?> var1, byte[] var2, Object[] var3) {
        return unsafe.defineAnonymousClass(var1, var2, var3);
    }

    public static Object allocateInstance(Class<?> var1) throws InstantiationException {
        return unsafe.allocateInstance(var1);
    }

    public static void throwException(Throwable var1) {
        unsafe.throwException(var1);
    }

    public static boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5) {
        return unsafe.compareAndSwapObject(var1, var2, var4, var5);
    }

    public static boolean compareAndSwapInt(Object var1, long var2, int var4, int var5) {
        return unsafe.compareAndSwapInt(var1, var2, var4, var5);
    }

    public static boolean compareAndSwapLong(Object var1, long var2, long var4, long var6) {
        return unsafe.compareAndSwapLong(var1, var2, var4, var6);
    }

    public static Object getObjectVolatile(Object var1, long var2) {
        return unsafe.getObjectVolatile(var1, var2);
    }

    public static void putObjectVolatile(Object var1, long var2, Object var4) {
        unsafe.putObjectVolatile(var1, var2, var4);
    }

    public static int getIntVolatile(Object var1, long var2) {
        return unsafe.getIntVolatile(var1, var2);
    }

    public static void putIntVolatile(Object var1, long var2, int var4) {
        unsafe.putIntVolatile(var1, var2, var4);
    }

    public static boolean getBooleanVolatile(Object var1, long var2) {
        return unsafe.getBooleanVolatile(var1, var2);
    }

    public static void putBooleanVolatile(Object var1, long var2, boolean var4) {
        unsafe.putBooleanVolatile(var1, var2, var4);
    }

    public static byte getByteVolatile(Object var1, long var2) {
        return unsafe.getByteVolatile(var1, var2);
    }

    public static void putByteVolatile(Object var1, long var2, byte var4) {
        unsafe.putByteVolatile(var1, var2, var4);
    }

    public static short getShortVolatile(Object var1, long var2) {
        return unsafe.getShortVolatile(var1, var2);
    }

    public static void putShortVolatile(Object var1, long var2, short var4) {
        unsafe.putShortVolatile(var1, var2, var4);
    }

    public static char getCharVolatile(Object var1, long var2) {
        return unsafe.getCharVolatile(var1, var2);
    }

    public static void putCharVolatile(Object var1, long var2, char var4) {
        unsafe.putCharVolatile(var1, var2, var4);
    }

    public static long getLongVolatile(Object var1, long var2) {
        return unsafe.getLongVolatile(var1, var2);
    }

    public static void putLongVolatile(Object var1, long var2, long var4) {
        unsafe.putLongVolatile(var1, var2, var4);
    }

    public static float getFloatVolatile(Object var1, long var2) {
        return unsafe.getFloatVolatile(var1, var2);
    }

    public static void putFloatVolatile(Object var1, long var2, float var4) {
        unsafe.putFloatVolatile(var1, var2, var4);
    }

    public static double getDoubleVolatile(Object var1, long var2) {
        return unsafe.getDoubleVolatile(var1, var2);
    }

    public static void putDoubleVolatile(Object var1, long var2, double var4) {
        unsafe.putDoubleVolatile(var1, var2, var4);
    }

    public static void putOrderedObject(Object var1, long var2, Object var4) {
        unsafe.putOrderedObject(var1, var2, var4);
    }

    public static void putOrderedInt(Object var1, long var2, int var4) {
        unsafe.putOrderedInt(var1, var2, var4);
    }

    public static void putOrderedLong(Object var1, long var2, long var4) {
        unsafe.putOrderedLong(var1, var2, var4);
    }

    public static void unpark(Object var1) {
        unsafe.unpark(var1);
    }

    public static void park(boolean var1, long var2) {
        unsafe.park(var1, var2);
    }

    public static int getLoadAverage(double[] var1, int var2) {
        return unsafe.getLoadAverage(var1, var2);
    }

    public static int getAndAddInt(Object var1, long var2, int var4) {
        return unsafe.getAndAddInt(var1, var2, var4);
    }

    public static long getAndAddLong(Object var1, long var2, long var4) {
        return unsafe.getAndAddLong(var1, var2, var4);
    }

    public static int getAndSetInt(Object var1, long var2, int var4) {
        return unsafe.getAndSetInt(var1, var2, var4);
    }

    public static long getAndSetLong(Object var1, long var2, long var4) {
        return unsafe.getAndSetLong(var1, var2, var4);
    }

    public static Object getAndSetObject(Object var1, long var2, Object var4) {
        return unsafe.getAndSetObject(var1, var2, var4);
    }

    public static void loadFence() {
        unsafe.loadFence();
    }

    public static void storeFence() {
        unsafe.storeFence();
    }

    public static void fullFence() {
        unsafe.fullFence();
    }

}