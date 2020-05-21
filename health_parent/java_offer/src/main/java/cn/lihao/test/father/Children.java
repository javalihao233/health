package cn.lihao.test.father;

public class Children extends Father {

    public Children() {
//        super();  实例化子类的时候，默认回调用父类的无参构造。如果父类没有无参构造，会要求子类继承父类的时候，重载子类的构造方法【用到了父类的有参构造】
        System.out.println("子类的构造执行了");
    }

    public static void main(String[] args) {

        Children children = new Children();

    }
}
