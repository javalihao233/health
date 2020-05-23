package com.lihao.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    private IUserDao userDao;

    public Object create(IUserDao userDao){
        this.userDao = userDao;

        //参数1
        ClassLoader classLoader = JdkProxy.class.getClassLoader();

        //参数2
        Class<?>[] interfaces = userDao.getClass().getInterfaces();

        //参数3    this：代理类本身

        return Proxy.newProxyInstance(classLoader,interfaces,this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyAspect myAspect = new MyAspect();
        myAspect.before_enhance();

        Object o = method.invoke(userDao, args);

        myAspect.after_enhance();
        return o;
    }
}
