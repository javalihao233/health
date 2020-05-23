package com.lihao.jdk2;

public class CglibTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        UserDaoImpl userDao = new UserDaoImpl();

        UserDaoImpl dao = (UserDaoImpl) cglibProxy.create(userDao);

        dao.getUser();
        System.out.println();
        dao.addUser();
    }
}
