package cn.lihao.test;

public class Demo6 {
    public static void main(String[] args) {
        System.out.println(test1());
    }


    public static String test1(){
        try {
            String s = "张三";
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            String ss = "李四";
            return ss;
        }finally {
            System.out.println("**************************");
        }
    }
}
