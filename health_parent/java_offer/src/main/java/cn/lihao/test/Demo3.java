package cn.lihao.test;

public class Demo3 {
    private String name;

    public Demo3() {
        System.out.println("无参构造方法");
    }

    public Demo3(String name) {
        //调用构造方法
        this();
        this.name = name;
        System.out.println("有参构造方法");
    }

    public void chengyuanff(){
        System.out.println("我是成员方法");
        Demo3 demo3 = new Demo3();
        System.out.println("*************");
    }

    public void test1(){
        //调用成员方法
        this.chengyuanff();
    }

    public static Object testTry(){
        int i1 = 1;
        try {
            int i2 = i1/0;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally执行了");
            return 5;
        }
    }

    public static void main(String[] args) {
        Demo3_1 demo3_1 = new Demo3_1();
        demo3_1.print();

        Demo3_1 demo3_11 = new Demo3_1();
        demo3_11.print();

        for (int i = 0; i < 10; i++) {

        System.out.println(3|7);
        System.out.println(3&7);
        }

        int i =8;
        int n =i++%4;

        System.out.println(i);
        System.out.println(n);

        Demo3 demo3 = new Demo3();
        demo3.chengyuanff();

        testTry();
    }
}
