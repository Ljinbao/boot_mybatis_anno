package com.lten.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lten.boot.handler.AbstractHandler;
import com.lten.boot.handler.HandlerContext;
import com.lten.boot.mapper.UserMapper;
import com.lten.boot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 9:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HandlerContext handlerContext;

    @Transactional()
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> allUsers = userMapper.findAllUsers();
        PageInfo<User> pageInfo = new PageInfo<>(allUsers);
        return pageInfo.getList();
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addPerson() {
        System.out.println("zengjia yiren ");
    }

    @Override
    public String handle(String type) {
        AbstractHandler instance = handlerContext.getInstance(type);
        return instance.handle(type);
    }

    @Override
    public User findUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        Optional<String> s = Optional.ofNullable(user).map(User::getName);
        System.out.println(s.get());
        Optional<String> stringOptional = Optional.of("zhangsan");
        stringOptional.ifPresent(e-> System.out.println("我被处理了。。。"+e));
        return Optional.ofNullable(user).orElse(new User());
    }
}
