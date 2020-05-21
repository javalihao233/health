package cn.lihao.test;

public class Demo4 {

    public static void main(String[] args) {
        System.out.println(testTry());
    }



    public static String testTry(){
        int num =1;

        try {
//            int result = num/0;
            return "1";
        } finally {
            System.out.println(2);
        }
    }
}
