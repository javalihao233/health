package cn.lihao.test;

public class Demo2 {

    public Demo2() {
    }

    public void ConstructTest(){
        Demo2();
    }

    public static void Demo2(){
        System.out.println(1);
    }

    public static void main(String[] args) {
        Demo2_1 demo2_1 = new Demo2_1();
        demo2_1.show();

        System.out.println("**********************");

        Demo2_1  demo2_2 = new Demo2_2();
        demo2_2.show();


        System.out.println();

        float x = 0f;
        System.out.println(x);
        x = 10/3;
        System.out.println(x);//3.0


        String a = "Hello";
        String substring = a.substring(0, 2);
        System.out.println(substring);

        Demo2();
    }
}
