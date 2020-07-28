package com.lihao.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyService {

    @Pointcut("execution(* com.lihao.service.*ServiceImpl.*(..))")
    public  void pc(){

    }


    //前置通知
    @Before("MyService.pc()")
    public  void before(){
        System.out.println("这是前置通知");
    }

    //后置通知
    @AfterReturning("execution(* com.lihao.service.*ServiceImpl.*(..))")
    public void afterReturning(){
        System.out.println("这是后置通知(如果出现异常不会调用)!!");
    }
}
