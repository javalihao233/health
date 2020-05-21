package cn.lihao.test.sort;

public class User implements Comparable{
    private int age;
    private String name;

    /**
     * 自定义排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        if (this.age-user.age>0) {
            return 1;
        }

        if (this.age-user.age==0) {
            return this.name.compareTo(user.name);
        }

        return -1;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
