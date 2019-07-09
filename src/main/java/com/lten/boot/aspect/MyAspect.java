package com.lten.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @version 1.0
 * @date 2019/5/21 13:04
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut(value = "execution(* com.lten.boot.service..*(..))")
    public void pointcut() {

    }



    @Before(value="pointcut()")
    public void before(){
        System.out.println("我是一个注解前置通知");
    }

    @Around(value="pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("环绕通知开始");
        Object result = joinPoint.proceed();
        System.out.println("环绕通知结束");
        return result;
    }

    @AfterThrowing(value="pointcut()",throwing="throwable")
    public void afterThrow(Throwable throwable){
        System.out.println("我是一个异常通知");
        System.out.println("异常的信息为："+throwable.getMessage());
    }


    @AfterReturning(value="pointcut()",returning="msg")
    public void afterReturn(Object msg){
        System.out.println("我是一个后置通知");
        System.out.println("获取返回值参数为："+msg);
    }
}
