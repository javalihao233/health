package cn.lihao.test;

public class Demo1 {

    public static void recur(int n_Input){
        if (n_Input > 0) {
            System.out.println(n_Input + "");
            recur(n_Input - 1);
            System.out.println(n_Input + "");
        }
    }

    public static void main(String[] args) {
        recur(10);
    }
}
