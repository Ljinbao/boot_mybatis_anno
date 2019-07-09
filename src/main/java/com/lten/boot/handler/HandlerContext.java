package com.lten.boot.handler;

import java.util.Map;

/**
 * @description 处理器上下文
 * @author admin
 * @version 1.0
 * @date 2019/5/22 15:31
 */
public class HandlerContext {
    private Map<String,Class> handlerMap;

    public HandlerContext(Map<String,Class> map){
        this.handlerMap = map;
    }

    public AbstractHandler getInstance(String type) {
        Class clazz = this.handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type:" + type);
        }
        AbstractHandler abstractHandler = null;
        try {
            abstractHandler = (AbstractHandler) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return abstractHandler;
    }
}
