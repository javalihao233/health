package cn.lihao.test;

public class OutClass {
    int p = 1;

    public class InnerClass{
        int p = 2;
        void inCal(){
            OutClass.this.p = 3;
        }
    }

    public static class AnotherInnerClass{
        int p = 4;
    }

    public static void main(String[] args) {
        OutClass oc = new OutClass();
//        InnerClass ic = new InnerClass();
        AnotherInnerClass ic2 = new AnotherInnerClass();
        AnotherInnerClass ic3 = new AnotherInnerClass();

    }
}
