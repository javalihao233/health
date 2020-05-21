package cn.lihao.test.singleton;

import java.util.ArrayList;

/**
 * 单例模式
 *
 * 1. 构造方法私有化
 * 2. 静态属性指向实例
 * 3. 静态方法返回静态变量指向的实例化对象
 */
public class SingleTonObje {
    //1. 构造方法私有化
    private SingleTonObje() {
    }

    //2. 静态属性指向实例
    private static SingleTonObje singleTonObje = new SingleTonObje();

    //3. 静态方法返回静态变量指向的实例化对象
    private static SingleTonObje getInstance(){
        return singleTonObje;
    }

    public static void main(String[] args) {
        SingleTonObje instance = SingleTonObje.getInstance();
        System.out.println(instance);//cn.lihao.test.singleton.SingleTonObje@266474c2
        System.out.println(instance.getClass());//class cn.lihao.test.singleton.SingleTonObje

        SingleTonObje obje = instance.singleTonObje;
        System.out.println(obje);//cn.lihao.test.singleton.SingleTonObje@266474c2


        ArrayList objects = new ArrayList<>();
        objects.add(123);
        objects.add("456");
        for (Object object : objects) {
            System.out.println(object);
        }
    }
}



