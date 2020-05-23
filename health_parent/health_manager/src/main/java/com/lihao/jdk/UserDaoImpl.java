package com.lihao.jdk;

public class UserDaoImpl implements IUserDao {
    @Override
    public void getUser() {
        System.out.println("查询了用户");
    }

    @Override
    public void addUser() {
        System.out.println("添加了用户");
    }
}
