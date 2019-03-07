package com.lten.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lten.boot.mapper.UserMapper;
import com.lten.boot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 9:58
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> allUsers = userMapper.findAllUsers();
        PageInfo<User> pageInfo = new PageInfo<>(allUsers);
        return pageInfo.getList();
    }
}
