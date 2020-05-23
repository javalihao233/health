package com.lihao.jdk;


/**
 * 切面类
 */
public class MyAspect {
    public void before_enhance(){
        System.out.println("前增强");
    }

    public void after_enhance(){
        System.out.println("后增强");
    }
}
