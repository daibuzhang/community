package com.laodai.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*@Component
@Aspect*/
public class AlphaAspect {
    //                  所有的返回值                 包下所有的业务，所有的方法 所有的参数
    @Pointcut("execution(* com.laodai.community.service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        System.out.println("before");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void AfterReturning(){
        System.out.println("AfterReturning");
    }

    @AfterThrowing("pointcut()")
    public void AfterThrowing(){
        System.out.println("AfterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
