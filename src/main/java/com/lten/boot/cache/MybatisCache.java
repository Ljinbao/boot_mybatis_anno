package com.lten.boot.cache;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @decription Mybatis二级缓存(定义第三方缓存)
 * @author lijinbao
 * @version 1.0
 * @date 2019/4/8 15:55
 */
@AllArgsConstructor
@NoArgsConstructor
public class MybatisCache implements Cache {

    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {


    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
