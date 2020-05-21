package cn.lihao.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Demo5 {

    public static void test1(){
        test1();
    }

    public static void main(String[] args) {

//        test1();

        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("name","张三");
        objectHashMap.put("age",16);
        objectHashMap.put("address","重庆");

        //*****************************************
        Set<String> strings = objectHashMap.keySet();
        for (String string : strings) {
            System.out.println("键："+string);
            System.out.println("值："+objectHashMap.get(string));
        }
        //*****************************************

        System.out.println("*************************************");

        //*****************************************
        Set<Map.Entry<String, Object>> entries = objectHashMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("键："+key);
            System.out.println("值："+value);
        }
        //*****************************************

        //返回Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        //返回Java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println(maxMemory/1024/1024);
        System.out.println(totalMemory/1024/1024);


        System.out.println("******************************************");
        Class<Demo5> demo5Class = Demo5.class;
        System.out.println(demo5Class);
        System.out.println(demo5Class.getClassLoader());
        System.out.println(demo5Class.getClassLoader().getParent());

    }
}
