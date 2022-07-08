package com.bknife.base;

import java.util.ArrayList;
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
}
