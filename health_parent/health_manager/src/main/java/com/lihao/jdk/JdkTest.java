package com.lihao.jdk;

public class JdkTest {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();

        IUserDao userDao = new UserDaoImpl();

        IUserDao dao = (IUserDao) jdkProxy.create(userDao);

        dao.getUser();
        System.out.println();
        dao.addUser();
    }
}
