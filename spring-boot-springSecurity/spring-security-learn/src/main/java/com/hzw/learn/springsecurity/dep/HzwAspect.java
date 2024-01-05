package com.hzw.learn.springsecurity.dep;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @ClassName HzwAspect
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/11
 **/
@Aspect
public class HzwAspect {


//    @Pointcut("execution(* com.hzw.learn.aop.aspectj.SomeTestBean.test1())")
    @Pointcut("@annotation(com.hzw.learn.springsecurity.dep.HzwPointAnno) && execution(* *(..))")
    private void hzwPointCut(){}

    @Before("hzwPointCut()")
    public void before(){
        System.out.println("hzwPointCut befor");
    }

    @After("hzwPointCut()")
    public void after(){
        System.out.println("hzwPointCut after");
    }

    //    @Around("hzwPointCut()")
//    public Object arountHzwPointProcess(final ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("hzwPointCut around befor");
////        System.out.println(Thread.currentThread().getStackTrace());
////        Thread.dumpStack();
//        Object obj;
//        try {
//            obj = joinPoint.proceed();
//        } finally {
//            System.out.println("hzwPointCut around after");
//        }
//        return obj;
//    }
}
