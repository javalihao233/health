package cn.lihao.test.sort;

import java.util.TreeSet;

public class ComparableTest {
    public static void main(String[] args) {

        test1();
        System.out.println();
        test2();

        System.out.println();
        TreeSet<String> treeSet = new TreeSet<>(new StringComparator());
        treeSet.add("李红");
        treeSet.add("李浩");
        treeSet.add("李香雪");
        treeSet.add("阿里巴巴");
        for (String s : treeSet) {
            System.out.print(s+",");//李红,李香雪,阿里巴巴,
        }

    }

    /**
     * 自定义排序————Comparable接口
     */
    public static void test1(){
        User user1 = new User(10, "张三");
        User user2 = new User(20, "李四");
        User user3 = new User(20, "王五");
        User user4 = new User(30, "张飞");

        TreeSet<User> users = new TreeSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        for (User user : users) {
            System.out.print(user+",");//User{age=10, name='张三'},User{age=20, name='李四'},User{age=20, name='王五'},User{age=30, name='张飞'},
        }
    }

    /**
     * 默认排序  升序
     */
    public static void test2(){
        TreeSet<Object> objects = new TreeSet<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        for (Object object : objects) {
            System.out.print(object+",");//1,2,3,
        }

        System.out.println();

        TreeSet<Object> objects1 = new TreeSet<>();
        objects1.add("zhangsan");
        objects1.add("lisi");
        objects1.add("wangwu");

        for (Object o : objects1) {
            System.out.print(o+",");//lisi,wangwu,zhangsan,
        }

    }
}
