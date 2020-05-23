package cn.lihao.test.map;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {


        HashMap hashMap = new HashMap();

        hashMap.put("a","alibaba");
        hashMap.put("b","boolean");
        hashMap.put("c","caocao");
        hashMap.put("d","double");


        System.out.println(hashMap.get("a"));
        System.out.println(hashMap.get("a").hashCode());
        System.out.println(hashMap.get("b").hashCode());
        System.out.println(hashMap.get("c").hashCode());
        System.out.println(hashMap.get("d").hashCode());

    }
}
