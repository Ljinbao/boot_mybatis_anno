package com.lten.boot.handler;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0
 * @date 2019/5/22 15:41
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {
    /**
     * 扫描注解HandlerType的包路径
     */
    private static final String PACKAGE_NAME = "com.lten.boot.handler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自己注册到Spring容器中去");
        Map<String, Class> handleMap = new HashMap<>(3);
        Reflections reflections = new Reflections(PACKAGE_NAME);
        reflections.getTypesAnnotatedWith(HandlerType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(HandlerType.class).value();
            handleMap.put(type,clazz);

        });
        //初始化HandlerContext并注册在Spring容器中
        HandlerContext handlerContext = new HandlerContext(handleMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(),handlerContext);
    }
}
