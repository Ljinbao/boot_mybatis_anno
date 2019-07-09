package com.lten.boot.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Thoms
 * @version 1.0
 * @description
 * @date 2019/6/6 9:03
 */
public class Cache {
    /**
     * 键值对集合
     */
    private final static Map<String, Entity> map = new HashMap<>();
    /**
     * 定时器线程池，用于清除过期缓存
     */
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * 添加缓存
     *
     * @param key  键
     * @param data 值
     */
    public synchronized static void put(String key, Object data) {
        Cache.put(key, data, 0);
    }

    /**
     * 添加缓存
     *
     * @param key    键
     * @param data   值
     * @param expire 过期时间，单位：毫秒， 0表示无限长
     */
    public synchronized static void put(String key, Object data, long expire) {
        //清除原键值对
        Cache.remove(key);
        //设置过期时间
        if (expire > 0) {
            Future future = executor.schedule(() -> {
                //过期后清除该键值对
                synchronized (Cache.class) {
                    map.remove(key);
                }
            }, expire, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(data, future));
        } else {
            //不设置过期时间
            map.put(key, new Entity(data, null));
        }
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return
     */
    public synchronized static Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 清除缓存
     *
     * @param key
     * @return
     */
    public synchronized static Object remove(String key) {
        //清除原缓存数据
        Entity entity = map.remove(key);
        if (entity == null) {
            return null;
        }
        //清除原键值对定时器
        Future future = entity.getFuture();
        if (future != null) {
            future.cancel(true);
        }
        return entity.getValue();
    }

    /**
     * 查询当前缓存的键值对数量
     *
     * @return
     */
    public synchronized static int size() {
        return map.size();
    }

    /**
     * 缓存实体类
     */
    private static class Entity {
        /**
         * 键值对的value
         */
        private Object value;
        /**
         * 定时器Future
         */
        private Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        /**
         * 获取值
         *
         * @return
         */
        public Object getValue() {
            return value;
        }

        /**
         * 获取Future对象
         *
         * @return
         */
        public Future getFuture() {
            return future;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String key = "id";
        //不设置过期时间
        System.out.println("***********不设置过期时间**********");
        Cache.put(key, 123);
        System.out.println("key:" + key + ", value:" + Cache.get(key));
        System.out.println("key:" + key + ", value:" + Cache.remove(key));
        System.out.println("key:" + key + ", value:" + Cache.get(key));
        //设置过期时间
        System.out.println("***********设置过期时间**********");
        Cache.put(key, "123456", 1000);
        System.out.println("key:" + key + ", value:" + Cache.get(key));
        System.out.println("key:" + key + ", value:" + Cache.get(key));
        /******************并发性能测试************/
        System.out.println("***********并发性能测试************");
        //创建有10个线程的线程池，将1000000次操作分10次添加到线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future[] futures = new Future[10];
        /********添加********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        Cache.put(Thread.currentThread().getId() + key + i, i, 5000);
                    }
                    return "添加键值：" + Thread.currentThread().getId() + key;
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                System.out.println(future.get());
            }
            System.out.printf("添加耗时：%dms ", System.currentTimeMillis() - start);
        }
        System.out.println("当前缓存容量：" + Cache.size());
        /********查询********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        Cache.get(Thread.currentThread().getId() + key + i);
                    }
                    return "查询键值：" + Thread.currentThread().getId() + key;
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                System.out.println(future.get());
            }
            System.out.printf("查询耗时：%dms", System.currentTimeMillis() - start);
        }
        System.out.println("当前缓存容量：" + Cache.size());

    }
}