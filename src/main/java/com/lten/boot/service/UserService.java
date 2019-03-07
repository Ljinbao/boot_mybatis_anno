package com.lten.boot.service;

import com.lten.boot.pojo.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
