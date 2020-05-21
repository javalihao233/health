package cn.lihao.test;

public class Demo2_2 extends Demo2_1 {

    float a = 3.0f;

    String d = "Java program";

    void show(){
        super.show();
        System.out.println("Class B：a = "+  a + "\td = " + d);
    }
}
