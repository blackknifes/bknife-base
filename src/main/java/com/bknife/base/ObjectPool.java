package com.bknife.base;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型对象池，通过内部LifeSpan管理生命周期
 */
public class ObjectPool<T> {
    /**
     * 生命周期接口
     */
    public static interface LifeSpan {
        default void init(Object... params) throws Exception {
        }

        public void release();
    }

    private Class<T> objectClass;
    private List<T> pool = new ArrayList<T>();

    public ObjectPool(Class<T> objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * 预分配对象
     * 
     * @param capacity
     */
    public void reserve(int capacity) {
        synchronized (pool) {
            if (capacity <= pool.size())
                return;
            capacity -= pool.size();
        }

        List<T> tempPool = new ArrayList<T>();
        try {
            for (int i = 0; i < capacity; i++)
                tempPool.add(objectClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (pool) {
            pool.addAll(tempPool);
        }
    }

    /**
     * 从对象池中获取对象
     * 
     * @return
     */
    public T get(Object... params) {
        T object = null;
        synchronized (pool) {
            if (!pool.isEmpty()) {
                int lastIndex = pool.size() - 1;
                object = pool.get(lastIndex);
                pool.remove(lastIndex);
            }
        }
        try {
            if (object == null)
                object = objectClass.newInstance();
            if (object instanceof ObjectPool.LifeSpan)
                ((ObjectPool.LifeSpan) object).init(params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }

    /**
     * 从对象池中获取多组对象
     * 
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] get(Collection<Object[]> params) {
        T[] objects = (T[]) Array.newInstance(objectClass, params.size());
        int index = 0;
        synchronized (pool) {
            int lastIndex = pool.size() - 1;
            for (; index < params.size(); index++) {
                if (pool.isEmpty())
                    break;
                objects[index] = pool.get(lastIndex);
                pool.remove(lastIndex);
                --lastIndex;
            }
        }
        try {
            for (int i = index; i < objects.length; i++)
                objects[i] = objectClass.newInstance();

            if (LifeSpan.class.isAssignableFrom(objectClass)) {
                index = 0;
                for (Object[] row : params)
                    ((LifeSpan) objects[index++]).init(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objects;
    }

    /**
     * 从对象池中获取多组对象
     * 
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] get(Object[][] params) {
        T[] objects = (T[]) Array.newInstance(objectClass, params.length);
        int index = 0;
        synchronized (pool) {
            int lastIndex = pool.size() - 1;
            for (; index < params.length; index++) {
                if (pool.isEmpty())
                    break;
                objects[index] = pool.get(lastIndex);
                pool.remove(lastIndex);
                --lastIndex;
            }
        }
        try {
            for (int i = index; i < objects.length; i++)
                objects[i] = objectClass.newInstance();

            if (LifeSpan.class.isAssignableFrom(objectClass)) {
                index = 0;
                for (Object[] row : params)
                    ((LifeSpan) objects[index++]).init(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objects;
    }

    /**
     * 回收对象到对象池
     * 
     * @param object
     */
    public void release(T object) {
        if (object instanceof ObjectPool.LifeSpan)
            ((ObjectPool.LifeSpan) object).release();
        synchronized (pool) {
            pool.add(object);
        }
    }

    /**
     * 批量回收容器内所有对象
     * 
     * @param objects
     */
    public void release(Collection<T> objects) {
        if (ObjectPool.LifeSpan.class.isAssignableFrom(objectClass)) {
            for (T object : objects)
                ((ObjectPool.LifeSpan) object).release();
        }

        synchronized (pool) {
            pool.addAll(objects);
        }
    }

    /**
     * 批量回收数组内所有对象
     * 
     * @param objects
     */
    public void release(T[] objects) {
        if (ObjectPool.LifeSpan.class.isAssignableFrom(objectClass)) {
            for (T object : objects)
                ((ObjectPool.LifeSpan) object).release();
        }

        synchronized (pool) {
            for (T object : objects)
                pool.add(object);
        }
    }
}
