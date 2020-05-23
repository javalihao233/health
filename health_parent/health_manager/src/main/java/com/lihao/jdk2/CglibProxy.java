package com.lihao.jdk2;

import com.lihao.jdk.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object create(Object object){
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(object.getClass());

        enhancer.setCallback(this);

        return enhancer.create();

    }


    /**
     *
     * @param o 根据指定父类生成的代理对象
     * @param method  拦截方法
     * @param objects   拦截方法的参数数组
     * @param methodProxy   方法的代理对象，用于执行父类的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MyAspect myAspect = new MyAspect();

        myAspect.before_enhance();
        Object obj = methodProxy.invokeSuper(o, objects);
        myAspect.after_enhance();
        return obj;
    }
}
