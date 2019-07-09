package com.lten.boot.service;

import com.lten.boot.pojo.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    List<User> findAll();

    void addPerson();

    String handle(String type);

    User findUserById(Integer id);
}
